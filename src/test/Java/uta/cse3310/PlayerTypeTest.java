package uta.cse3310;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTypeTest {

    @Test
    public void testPlayerTypeValues() {
        PlayerType[] expectedValues = {PlayerType.NOPLAYER, PlayerType.XPLAYER, PlayerType.HUMAN};
        PlayerType[] actualValues = PlayerType.values();
        
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void testPlayerTypeValueOf() {
        assertEquals(PlayerType.NOPLAYER, PlayerType.valueOf("NOPLAYER"));
        assertEquals(PlayerType.XPLAYER, PlayerType.valueOf("XPLAYER"));
        assertEquals(PlayerType.HUMAN, PlayerType.valueOf("HUMAN"));
    }
}

