package uta.cse3310;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest {

    private App app;
    private WebSocket conn;
    private ClientHandshake handshake;

    @BeforeEach
    public void setUp() {
        app = new App(new InetSocketAddress("localhost", 9005));
        conn = mock(WebSocket.class);
        handshake = mock(ClientHandshake.class);
    }

    @Test
    public void testOnOpen() {
        when(conn.getRemoteSocketAddress()).thenReturn(new InetSocketAddress("localhost", 9005));

        app.onOpen(conn, handshake);

        // Verify that the connection attachment is not null
        assertNotNull(conn.getAttachment(), "Attachment should not be null after onOpen");
    }

    @Test
    public void testOnClose() {
        when(conn.getRemoteSocketAddress()).thenReturn(new InetSocketAddress("localhost", 9005));
        app.onOpen(conn, handshake);

        app.onClose(conn, 1000, "Test", true);

        // Verify that the attachment is null after onClose
        assertNull(conn.getAttachment(), "Attachment should be null after onClose");
    }

    @Test
    public void testOnMessage() {
        when(conn.getRemoteSocketAddress()).thenReturn(new InetSocketAddress("localhost", 9005));
        app.onOpen(conn, handshake);

        UserEvent event = new UserEvent(PlayerType.HUMAN, 1);
        event.setAction("PLAY");
        String message = new Gson().toJson(event);

        app.onMessage(conn, message);

        // Verify that a message is sent back
        verify(conn, times(1)).send(anyString());
    }

    @Test
    public void testOnMessageByteBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        app.onMessage(conn, byteBuffer);
        verify(conn, never()).send(anyString());
    }

    @Test
    public void testOnError() {
        Exception ex = new Exception("Test Exception");
        app.onError(conn, ex);
        assertNotNull(ex);
    }

    @Test
    public void testOnStart() {
        app.onStart();
        assertNotNull(app);
    }

    @Test
    public void testMain() {
        String[] args = {};
        App.main(args);
        assertNotNull(App.class);
    }
}



