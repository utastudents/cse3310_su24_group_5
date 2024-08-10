package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
/*
    private App app;
    private Gson gson = new Gson();

    @BeforeEach
    public void setup() {
        app = new App(9105);
        app.setReuseAddr(true);
        app.start();
    }

    @Test
    public void testWebSocketConnection() throws Exception {
        WebSocketClient client = new WebSocketClient(new URI("ws://localhost:9105")) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                // Send a sample UserEvent JSON
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("playerType", "HUMAN");
                jsonObject.addProperty("gameId", 1);
                send(jsonObject.toString());
            }

            @Override
            public void onMessage(String message) {
                // Check if the message matches the expected JSON format
                JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
                assertNotNull(jsonObject);
                assertTrue(jsonObject.has("gameId"));
                close();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };
        client.connectBlocking();
    }*/
}






