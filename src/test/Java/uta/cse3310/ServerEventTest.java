package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerEventTest {

    private ServerEvent serverEvent;

    @BeforeEach
    public void setUp() {
        serverEvent = new ServerEvent();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(serverEvent.YouAre);
        assertEquals(0, serverEvent.GameId);
    }

    @Test
    public void testParameterizedConstructor() {
        ServerEvent event = new ServerEvent(PlayerType.HUMAN, 123);
        assertEquals(PlayerType.HUMAN, event.YouAre);
        assertEquals(123, event.GameId);
    }

    @Test
    public void testPublicFields() {
        serverEvent.YouAre = PlayerType.XPLAYER;
        serverEvent.GameId = 456;
        assertEquals(PlayerType.XPLAYER, serverEvent.YouAre);
        assertEquals(456, serverEvent.GameId);
    }
}
