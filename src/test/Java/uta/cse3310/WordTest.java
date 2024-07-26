package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {

    private Word word;
    private String[] wordList = {"apple", "banana", "cherry"};

    @BeforeEach
    public void setUp() {
        word = new Word(wordList);
    }

    @Test
    public void testGetSelectedWord() {
        String selectedWord = word.getSelectedWord();
        assertTrue(selectedWord.equals("apple") || selectedWord.equals("banana") || selectedWord.equals("cherry"));
    }

    @Test
    public void testGuessLetter() {
        String selectedWord = word.getSelectedWord();
        boolean result = word.guessLetter(selectedWord.charAt(0));
        assertTrue(result);
    }

    @Test
    public void testGetWordProgress() {
        String selectedWord = word.getSelectedWord();
        word.guessLetter(selectedWord.charAt(0));
        String progress = word.getWordProgress();
        assertTrue(progress.contains(String.valueOf(selectedWord.charAt(0))));
    }

    @Test
    public void testIsFullyGuessed() {
        String selectedWord = word.getSelectedWord();
        for (char c : selectedWord.toCharArray()) {
            word.guessLetter(c);
        }
        assertTrue(word.isFullyGuessed());
    }

    @Test
    public void testReset() {
        String initialWord = word.getSelectedWord();
        word.reset();
        String newWord = word.getSelectedWord();
        assertNotEquals(initialWord, newWord, "The selected word should change after reset");
    }
}


