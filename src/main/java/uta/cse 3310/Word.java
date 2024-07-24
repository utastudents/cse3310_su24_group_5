package uta.cse3310;

import java.util.Random;

public class Word {

    private String[] words;
    private String selectedWord;
    private boolean[] guessedLetters;

    public Word(String[] wordList) {
        this.words = wordList;
        selectRandomWord();
    }

    private void selectRandomWord() {
        Random rand = new Random();
        selectedWord = words[rand.nextInt(words.length)];
        guessedLetters = new boolean[selectedWord.length()];
    }

    public String getSelectedWord() {
        return selectedWord;
    }

    public boolean guessLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                guessedLetters[i] = true;
                found = true;
            }
        }
        return found;
    }

    public String getWordProgress() {
        StringBuilder progress = new StringBuilder();
        for (int i = 0; i < selectedWord.length(); i++) {
            if (guessedLetters[i]) {
                progress.append(selectedWord.charAt(i));
            } else {
                progress.append('_');
            }
        }
        return progress.toString();
    }

    public boolean isFullyGuessed() {
        for (boolean guessed : guessedLetters) {
            if (!guessed) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        selectRandomWord();
    }
}

