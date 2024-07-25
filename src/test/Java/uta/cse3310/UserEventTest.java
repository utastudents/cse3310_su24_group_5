package uta.cse3310;
//package org.junit.jupiter.api;
//import org.junit.jupiter.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uta.cse3310.PlayerType;  // Correctly import PlayerType
import uta.cse3310.UserEvent;  // Correctly import UserEvent

public class UserEventTest {
    private UserEvent userEvent;

    @BeforeEach
    public void setUp() {
        userEvent = new UserEvent(PlayerType.NOPLAYER, 1);
        userEvent.setPlayerId("player1");
        userEvent.setAction("someAction");
        userEvent.setValue("someValue");
    }

    @Test
    public void testUserEvent() {
        assertEquals("player1", userEvent.getPlayerId());
        assertEquals("someAction", userEvent.getAction());
        assertEquals("someValue", userEvent.getValue());
    }
}






