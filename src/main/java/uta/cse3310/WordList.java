package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class WordList {

    private ArrayList<String> randomwords = new ArrayList<>();

    public void printwords() {
        for (String word : randomwords) {
            System.out.println("word selected " + word);
        }
    }

    public void gatherwords() {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        System.out.println("I am selecting " + randomNumber + " words");

        for (int i = 0; i < randomNumber; i++) {
            String w = readWordsFromFile("src/main/resources/words.txt");
            randomwords.add(w);
        }
    }

    public static String readWordsFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            System.err.println("abort abort failed!!!");
        }
        return isValidWord(words);
    }

    public static String isValidWord(ArrayList<String> words) {
        String validword = "";
        while (validword.equals("")) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(words.size());
            String testword = words.get(randomIndex);
            String rule = "^[a-z]+$";
            if (testword.matches(rule) && testword.length() >= 3 && testword.length() <= 12) {
                validword = testword;
            }
        }
        return validword;
    }

    public ArrayList<String> getArrList() {
        return randomwords;
    }

    public static HashSet<Character> findletters(ArrayList<String> wordsforgame) {
        HashSet<Character> letters = new HashSet<>();
        for (String word : wordsforgame) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                letters.add(letter);
            }
        }
        return letters;
    }
}



