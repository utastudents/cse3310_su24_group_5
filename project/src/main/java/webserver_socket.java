import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class webserver_socket extends WebSocketServer {
    public static final int SERVER_PORT=8080;

    public static void main(String[] args) {

      var server = new webserver_socket();
      server.start();

    }
    public webserver_socket() {
    super( new InetSocketAddress(SERVER_PORT));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
    System.out.println("Connection Opened"+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Connection Closed"+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("Message received  "+ s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.out.println("Error for"+webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onStart() {
        System.out.println("Server listening on port "+ getPort());
    }
}
