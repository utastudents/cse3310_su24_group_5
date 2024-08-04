package uta.cse3310;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Word {
    private ArrayList<String> wordsforgame;

    public Word(ArrayList<String> wordsforgame) {
        this.wordsforgame = wordsforgame;
    }

    public static int iscorrect(char letter, HashSet<Character> lettersinword) {
        return lettersinword.contains(letter) ? 1 : 0;
    }

    public static void checksolutionandreveal(ArrayList<String> wordsforgame, char letter, HashSet<Character> lettersinword, HashSet<Character> lettersguessed, HashSet<Character> correctguesses) {
        lettersguessed.add(letter);
        int i = iscorrect(letter, lettersinword);
        if (i == 1) {
            correctguesses.add(letter);
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect!!!");
        }
        if (correctguesses.equals(lettersinword)) {
            System.out.println("You guessed all the letters!");
            winner(wordsforgame);
        }
        getWordProgress(wordsforgame, correctguesses);
    }

    public static void getWordProgress(ArrayList<String> wordsforgame, HashSet<Character> correctguesses) {
        String result = String.join(" ", wordsforgame);
        for (int i = 0; i < result.length(); i++) {
            if (correctguesses.contains(result.charAt(i))) {
                System.out.print(result.charAt(i));
            } else {
                System.out.print("_");
            }
        }
    }

    public static void winner(ArrayList<String> wordsforgame) {
        System.out.print("\nWinner the word(s) were:");
        for (String word : wordsforgame) {
            System.out.print(" " + word);
        }
        System.out.println();
    }

    public boolean solve(String solution) {
        String combinedWords = String.join("", wordsforgame);
        return combinedWords.equals(solution);
    }
}




