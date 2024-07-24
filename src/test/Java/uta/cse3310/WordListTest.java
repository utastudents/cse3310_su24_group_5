package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordListTest {

    private WordList wordList;

    @BeforeEach
    public void setUp() {
        wordList = new WordList("path/to/words.txt");
    }

    @Test
    public void testWordListLoad() {
        assertNotNull(wordList.getWords());
        assertTrue(wordList.getWords().size() > 0);
    }
}
