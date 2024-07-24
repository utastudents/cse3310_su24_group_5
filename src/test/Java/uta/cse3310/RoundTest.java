import uta.cse3310.Round;
import uta.cse3310.Word;
import uta.cse3310.Stake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class RoundTest {
    private Round round;

    @BeforeEach
    public void setUp() {
        List<uta.cse3310.Player> players = new ArrayList<>();
        round = new Round(players, "word", "anotherWord"); // Use appropriate parameters
    }

    @Test
    public void testSomething() {
        // Test code here
    }
}



