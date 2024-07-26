package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordListTest {

    private WordList wordList;
    private static final String TEST_FILE_PATH = "src/test/resources/test_words.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file with test words
        List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry");
        Files.write(Path.of(TEST_FILE_PATH), words);

        wordList = new WordList(TEST_FILE_PATH);
    }

    @Test
    public void testLoadWords() {
        List<String> words = wordList.getWords();
        assertNotNull(words);
        assertEquals(5, words.size());
        assertTrue(words.contains("apple"));
        assertTrue(words.contains("banana"));
        assertTrue(words.contains("cherry"));
        assertTrue(words.contains("date"));
        assertTrue(words.contains("elderberry"));
    }

    @Test
    public void testGetRandomWord() {
        String randomWord = wordList.getRandomWord();
        assertNotNull(randomWord);
        assertTrue(wordList.getWords().contains(randomWord));
    }

    @Test
    public void testGetWords() {
        List<String> words = wordList.getWords();
        assertNotNull(words);
        assertEquals(5, words.size());
    }
}

