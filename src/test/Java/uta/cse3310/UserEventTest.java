package uta.cse3310;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserEventTest {

    @Test
    void testUserEvent() {
        UserEvent event = new UserEvent(PlayerType.HUMAN, 1, "PLAY", "someValue");

        assertEquals(PlayerType.HUMAN, event.getPlayerType());
        assertEquals(1, event.getId());
        assertEquals("PLAY", event.getAction());
        assertEquals("someValue", event.getValue());

        event.setPlayerId("player123");
        assertEquals("player123", event.getPlayerId());
    }
}








