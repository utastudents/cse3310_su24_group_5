package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordListTest {

    private WordList wordList;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        wordList = new WordList();
    }

    @Test
    void testGatherWords() throws IOException {
        // Prepare a temporary file with some words
        File tempFile = tempDir.resolve("words.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("apple\n");
            writer.write("banana\n");
            writer.write("cherry\n");
            writer.write("date\n");
            writer.write("fig\n");
        }

        // Override the file path in WordList class (assuming it's not final and can be set)
        WordList.readWordsFromFile(tempFile.getAbsolutePath());

        wordList.gatherwords();

        // Check if the number of gathered words is between 1 and 3
        assertTrue(wordList.getArrList().size() >= 1 && wordList.getArrList().size() <= 3);
    }

    @Test
    void testReadWordsFromFile() throws IOException {
        // Prepare a temporary file with some words
        File tempFile = tempDir.resolve("words.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("apple\n");
            writer.write("banana\n");
            writer.write("cherry\n");
        }

        String word = WordList.readWordsFromFile(tempFile.getAbsolutePath());
        assertNotNull(word);
        assertTrue(word.matches("^[a-z]+$"));
        assertTrue(word.length() >= 3 && word.length() <= 12);
    }

    @Test
    void testIsValidWord() {
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("invalid_word_123");
        words.add("cherry");

        String validWord = WordList.isValidWord(words);
        assertNotNull(validWord);
        assertTrue(validWord.matches("^[a-z]+$"));
        assertTrue(validWord.length() >= 3 && validWord.length() <= 12);
    }

    @Test
    void testGetArrList() {
        ArrayList<String> words = wordList.getArrList();
        assertNotNull(words);
        assertTrue(words.isEmpty());
    }
}


