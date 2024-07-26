package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEventTest {

    private UserEvent userEvent;

    @BeforeEach
    public void setUp() {
        userEvent = new UserEvent(PlayerType.HUMAN, 1);
    }

    @Test
    public void testSetAndGetPlayerId() {
        userEvent.setPlayerId("player1");
        assertEquals("player1", userEvent.getPlayerId());
    }

    @Test
    public void testSetAndGetAction() {
        userEvent.setAction("PLAY");
        assertEquals("PLAY", userEvent.getAction());
    }

    @Test
    public void testSetAndGetValue() {
        userEvent.setValue("someValue");
        assertEquals("someValue", userEvent.getValue());
    }

    @Test
    public void testGetPlayerType() {
        assertEquals(PlayerType.HUMAN, userEvent.getPlayerType());
    }

    @Test
    public void testGetId() {
        assertEquals(1, userEvent.getId());
    }
}







