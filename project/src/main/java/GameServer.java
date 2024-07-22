import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends WebSocketServer {
    private List<Game> games;
    private List<WebSocket> clients;

    public GameServer(int webSocketPort) {
        super(new InetSocketAddress(webSocketPort));
        this.games = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void startServer() {
        start();
        System.out.println("Server started on port: " + getPort());
    }

    public void stopServer() {
        try {
            stop();
            System.out.println("Server stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("Connection opened: " + webSocket.getRemoteSocketAddress());
        clients.add(webSocket);
        if (clients.size() >= 2 && clients.size() <= 4) {
            startNewGame();
        }
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("Connection closed: " + webSocket.getRemoteSocketAddress());
        clients.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Message received: " + message);
        handleClientMessage(webSocket, message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully.");
    }

    private void handleClientMessage(WebSocket webSocket, String message) {
        String[] parts = message.split(":");
        String action = parts[0];
        String payload = parts[1];

        Game game = games.get(0); // Assuming only one game for simplicity
        Player currentPlayer = game.getCurrentPlayer();

        switch (action) {
            case "solve":
                boolean correct = currentPlayer.solvePuzzle(payload, game.getCurrentRound());
                if (correct) {
                    game.endRound();
                    broadcast("solve:correct:" + currentPlayer.getName());
                } else {
                    game.nextPlayer();
                    broadcast("solve:incorrect:" + currentPlayer.getName());
                }
                break;
            case "buy":
                currentPlayer.buyVowel();
                game.getCurrentRound().revealLetter(payload.charAt(0));
                broadcast("buy:" + currentPlayer.getName() + ":" + payload);
                game.nextPlayer();
                break;
            case "consonant":
                currentPlayer.selectConsonant(payload.charAt(0), game.getCurrentRound());
                game.nextPlayer();
                broadcast("consonant:" + currentPlayer.getName() + ":" + payload);
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }

        // Send updated game state to all clients
        sendGameState();
    }

    private void startNewGame() {
        Game game = new Game(clients.size());
        games.add(game);
        game.startGame();
        // Notify clients that the game has started
        for (WebSocket client : clients) {
            client.send("Game started!");
        }
        sendGameState();
    }

    public void broadcast(String message) {
        for (WebSocket client : clients) {
            client.send(message);
        }
    }

    private void sendGameState() {
        Game game = games.get(0); // Assuming only one game for simplicity
        Round currentRound = game.getCurrentRound();
        StringBuilder state = new StringBuilder("update:");

        // Add the words and their revealed letters to the state
        for (Word word : currentRound.getWords()) {
            state.append(word.getRevealedWord()).append(" ");
        }

        broadcast(state.toString().trim());
    }

    public static void main(String[] args) {
       GameServer server = new GameServer(8080);
        server.startServer();
        WordList wordList = new WordList("C:\\Users\\kha\\Desktop\\project\\src\\main\\resources\\words.txt");
        List<String> randomWords = wordList.getRandomWords(3);
        for (String word : randomWords) {
            System.out.println("Word selected: " + word);
        }
        

    }
}
