import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Word> words = new ArrayList<>();
    private List<Stake> stakes = new ArrayList<>();
    private int turns;

    public Round() {
        this.turns = 0;
    }

    public void startRound() {
        turns = 0;
        words.clear(); // Clear previous words
        stakes.clear(); // Clear previous stakes
        // Add logic to populate words and stakes if necessary
        System.out.println("Round started.");
    }

    public void endRound() {
        System.out.println("Round ended.");
    }

    public int selectWord() {
        if (!words.isEmpty()) {
            Word selectedWord = words.get(0); // Replace with actual selection logic
            System.out.println("Selected word: " + selectedWord.getValue());
            return words.indexOf(selectedWord);
        }
        return -1; // Indicate no word selected
    }

    public int selectStake() {
        if (!stakes.isEmpty()) {
            Stake selectedStake = stakes.get(0); // Replace with actual selection logic
            System.out.println("Selected stake: " + selectedStake.getValue());
            return stakes.indexOf(selectedStake);
        }
        return -1; // Indicate no stake selected
    }
       

    // for testing
    public void addWord(Word word) {
        words.add(word);
    }

    public void addStake(Stake stake) {
        stakes.add(stake);
    }

    public List<Word> getWords() {
        return words;
    }

    public List<Stake> getStakes() {
        return stakes;
    }

    public int getTurns() {
        return turns;
    }
}


