package uta.cse3310;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserEventTest {

    @Test
    void testUserEvent() {
        UserEvent event = new UserEvent("Play", "27", "45");

        //assertEquals(PlayerType.HUMAN, event.getPlayerType());
        assertEquals("Play", event.getAction());
        assertEquals("27", event.getValue());
        assertEquals("45", event.getPlayerId());

        event.setPlayerId("player123");
        assertEquals("player123", event.getPlayerId());
    }
}









