package uta.cse3310;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
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

    public Vector<Game> getGames() {
        return activeGames;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connectionId++;
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

        ServerEvent event = new ServerEvent(PlayerType.NOPLAYER, gameId);
        Game game = null;

        for (Game g : activeGames) {
            if (g.getPlayers().size() < 4) { // Assuming a maximum of 4 players per game
                game = g;
                System.out.println("Found a match");
                break;
            }
        }

        Player newPlayer = new Player("Player" + connectionId, PlayerType.HUMAN);

        if (game == null) {
            List<Player> players = new ArrayList<>();
            players.add(newPlayer); // Initialize at least one player

            try {
                game = new Game(players, "src/main/resources/words.txt", "src/main/resources/stakes.txt",
                        new Statistics());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            game.setGameId(gameId++);
            activeGames.add(game);

            System.out.println("Creating a new Game");
        } else {
            game.addPlayer(newPlayer);
            System.out.println("Joining an existing game");
        }

        conn.setAttachment(game); // Attach the game to the connection

        Gson gson = new Gson();

        String playerJson = gson.toJson(newPlayer.getId());
        conn.send(playerJson);
        System.out.println("> Player ID" + playerJson);

        String gameJson = gson.toJson(event);
        conn.send(gameJson);
        System.out.println("> " + gameJson);

        gameJson = gson.toJson(game);
        // System.out.println("< " + gameJson);
        broadcastToGame(game, gameJson); // Broadcast to the specific game

        if (game.getPlayers().size() == 2) {
            game.startGame();
        }
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
        Gson gson = new GsonBuilder().create();
        UserEvent event = gson.fromJson(message, UserEvent.class);

        Game game = conn.getAttachment();
        if (game != null) {
            Player currentPlayer = game.getCurrentRound().getCurrentPlayer();

            //if (!event.getPlayerId().equals(currentPlayer.getId())) {
              //  System.out.println("It's not this player's turn.");
                //return; // Ignore the action if it's not the current player's turn
            //}

            //System.out.println("Received message from player ID: " + event.getPlayerId() + " with action: " + event.getAction());
            
            game.update(event);
            
            // After processing the input, determine if the turn should continue or advance
            if (!game.getCurrentRound().isRoundActive()) {
                game.moveToNextRoundOrEndGame();
            } else if (!game.correctGuess){
                // Advance to the next turn
                game.getCurrentRound().advanceTurn();
            }

            String jsonString = gson.toJson(game);
            broadcastToGame(game, jsonString);
        } else {
            System.out.println("No game attached to the WebSocket connection.");
        }
        //System.out.println("Finished onMessage for player ID: " + event.getPlayerId());
          
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

    private void broadcastToGame(Game game, String message) {
        System.out.println("Broadcasting to game: " + game.getGameId());
        for (WebSocket conn : getConnections()) { // Changed from connections() to getConnections()
            if (conn.getAttachment() == game) {
                conn.send(message);
            }
        }
    }

    public static void main(String[] args) {
        String httpPortEnv = System.getenv("HTTP_PORT");
        int httpPort = (httpPortEnv != null) ? Integer.parseInt(httpPortEnv) : 9005;

        HttpServer httpServer = new HttpServer(httpPort, "./html");
        httpServer.start();
        System.out.println("HTTP server started on port: " + httpPort);

        String wsPortEnv = System.getenv("WEBSOCKET_PORT");
        int wsPort = (wsPortEnv != null) ? Integer.parseInt(wsPortEnv) : 9105;

        App webSocketServer = new App(wsPort);
        webSocketServer.setReuseAddr(true);
        webSocketServer.start();
        System.out.println("WebSocket server started on port: " + wsPort);
    }
}