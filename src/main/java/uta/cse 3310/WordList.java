import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {
    
    private String filePath;
    private Random random;

    public WordList(String filePath) {
        this.filePath = filePath;
        this.random = new Random();
    }

    public List<String> getRandomWords(int numWords) {
        List<String> randomWords = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
            String word = readRandomWordFromFile();
            if (word != null) {
                randomWords.add(word);
            }
        }
        return randomWords;
    }

    private String readRandomWordFromFile() {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + filePath);
            e.printStackTrace();
            return null;
        }
        return getRandomValidWord(words);
    }

    private String getRandomValidWord(List<String> words) {
        List<String> validWords = new ArrayList<>();
        String rule = "^[a-z]+$";
        for (String word : words) {
            if (word.matches(rule) && word.length() >= 3 && word.length() <= 12) {
                validWords.add(word);
            }
        }

        if (validWords.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(validWords.size());
        return validWords.get(randomIndex);
    }
}
