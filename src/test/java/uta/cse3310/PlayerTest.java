package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PlayerTest {/*

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", PlayerType.HUMAN);
    }

    @Test
    void testInitialValues() {
        assertNotNull(player.getId());
        assertEquals("TestPlayer", player.getName());
        assertEquals(1000, player.getScore());
        assertFalse(player.isWinner());
        assertNotNull(player.getTimer());
        assertTrue(player.getBoughtVowels().isEmpty());
        assertTrue(player.getGuessedConsonants().isEmpty());
        assertEquals(PlayerType.HUMAN, player.getPlayerType());
    }

    @Test
    void testAddScore() {
        player.addScore(100);
        assertEquals(1100, player.getScore());
    }

    @Test
    void testDeductScore() {
        player.deductScore(100);
        assertEquals(900, player.getScore());
    }

    @Test
    void testResetScore() {
        player.resetScore();
        assertEquals(0, player.getScore());
    }

    @Test
    void testBuyVowel() {
        char vowel = 'a';
        player.buyVowel(vowel);
        List<Character> boughtVowels = player.getBoughtVowels();
        assertTrue(boughtVowels.contains(vowel));
        assertTrue(player.hasBoughtVowel(vowel));
    }

    @Test
    void testGuessConsonant() {
        char consonant = 'b';
        player.guessConsonant(consonant);
        List<Character> guessedConsonants = player.getGuessedConsonants();
        assertTrue(guessedConsonants.contains(consonant));
        assertTrue(player.hasGuessedConsonant(consonant));
    }

    @Test
    void testSetAndGetWinner() {
        player.setWinner(true);
        assertTrue(player.isWinner());
    }

    @Test
    void testSetAndGetPlayerType() {
        player.setPlayerType(PlayerType.XPLAYER);
        assertEquals(PlayerType.XPLAYER, player.getPlayerType());
    }*/
}


