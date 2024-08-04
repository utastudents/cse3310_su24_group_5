package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class WordListTest {
    private WordList wordList;

    @BeforeEach
    void setUp() {
        wordList = new WordList();
    }

    @Test
    void testGatherWords() {
        wordList.gatherwords();
        assertFalse(wordList.getArrList().isEmpty());
    }

    @Test
    void testReadWordsFromFile() {
        String word = WordList.readWordsFromFile("cse3310_su24_group_5/src/main/resources/words");
        assertNotNull(word);
    }

    @Test
    void testIsValidWord() {
        ArrayList<String> words = new ArrayList<>();
        words.add("valid");
        words.add("123");
        words.add("invalid!");
        String validWord = WordList.isValidWord(words);
        assertEquals("valid", validWord);
    }

    @Test
    void testFindLetters() {
        ArrayList<String> words = new ArrayList<>();
        words.add("example");
        HashSet<Character> letters = WordList.findletters(words);
        assertTrue(letters.contains('e'));
        assertTrue(letters.contains('x'));
        assertFalse(letters.contains('z'));
    }

    @Test
    void testGetArrList() {
        wordList.gatherwords();
        ArrayList<String> words = wordList.getArrList();
        assertNotNull(words);
        assertFalse(words.isEmpty());
    }
}

