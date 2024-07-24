package uta.cse3310;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {

    private Vector<Game> activeGames = new Vector<>();
    private int gameId = 1;
    private int connectionId = 0;
    private Statistics stats;
    private HttpServer httpServer;

    public App(int port) {
        super(new InetSocketAddress(port));
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connectionId++;
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

        ServerEvent event = new ServerEvent();
        Game game = null;

        for (Game g : activeGames) {
            if (g.getPlayers().size() < 4) {  // Assuming a maximum of 4 players per game
                game = g;
                System.out.println("found a match");
                break;
            }
        }

        if (game == null) {
            List<Player> players = new ArrayList<>(); // Add logic to initialize players
            try {
                game = new Game(players, "path/to/words.txt", "path/to/stakes.txt", new Statistics());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            game.setGameId(gameId++);
            activeGames.add(game);
            System.out.println("creating a new Game");
        } else {
            System.out.println("joining an existing game");
        }

        conn.setAttachment(game);
        Gson gson = new Gson();
        String jsonString = gson.toJson(event);
        conn.send(jsonString);
        System.out.println("> " + jsonString);

        jsonString = gson.toJson(game);
        System.out.println("< " + jsonString);
        broadcast(jsonString);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has closed");
        Game game = conn.getAttachment();
        if (game != null) {
            game.removePlayer(conn);
            if (game.getPlayers().isEmpty()) {
                activeGames.remove(game);
            }
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("< " + message);
        Gson gson = new GsonBuilder().create();
        UserEvent event = gson.fromJson(message, UserEvent.class);

        Game game = conn.getAttachment();
        if (game != null) {
            game.update(event);
            String jsonString = gson.toJson(game);
            System.out.println("> " + jsonString);
            broadcast(jsonString);
        }
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        setConnectionLostTimeout(0);
        stats = new Statistics();
        System.out.println("WebSocket server started successfully");
    }

    public static void main(String[] args) {
        String httpPortEnv = System.getenv("HTTP_PORT");
        int httpPort = (httpPortEnv != null) ? Integer.parseInt(httpPortEnv) : 8080;

        HttpServer httpServer = new HttpServer(httpPort, "./html");
        httpServer.start();
        System.out.println("HTTP server started on port: " + httpPort);

        String wsPortEnv = System.getenv("WEBSOCKET_PORT");
        int wsPort = (wsPortEnv != null) ? Integer.parseInt(wsPortEnv) : 8081;

        App webSocketServer = new App(wsPort);
        webSocketServer.setReuseAddr(true);
        webSocketServer.start();
        System.out.println("WebSocket server started on port: " + wsPort);
    }
}




