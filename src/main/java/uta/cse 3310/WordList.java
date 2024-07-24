package uta.cse3310;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    private List<String> words;

    public WordList(String filePath) throws IOException {
        loadWords(filePath);
    }

    private void loadWords(String filePath) throws IOException {
        words = Files.readAllLines(Paths.get(filePath));
    }

    public String getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public List<String> getWords() {
        return new ArrayList<>(words);
    }

    public static void main(String[] args) {
        try {
            WordList wordList = new WordList("path/to/words.txt");
            System.out.println("Random word: " + wordList.getRandomWord());
        } catch (IOException e) {
            System.err.println("Error loading words: " + e.getMessage());
        }
    }
}

