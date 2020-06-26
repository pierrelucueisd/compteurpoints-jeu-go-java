import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    GameController gc;

    @BeforeEach
    void setUp() {
        int boardSize = 9;
        gc = new GameController(boardSize);
    }

    void testBoard(String input, String expected) {
        gc.startGame(input);
        String result = gc.board.toString();
        assertEquals(result, expected);
    }

    @Test
    void test1() {
        String actions = "G7 G6 F6 G5 H5 H6 J6 H7 H8";
        testBoard(actions, "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-*-+\n" +
                "+-+-+-+-+-+-*-o-+\n" +
                "+-+-+-+-+-*-o-o-*\n" +
                "+-+-+-+-+-+-o-*-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n");
    }

    @Test
    void test2() {
        String actions = "G7 H7 J7 H6 H8 PASS G6 PASS J6 PASS H5";
        testBoard(actions, "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-*-+\n" +
                "+-+-+-+-+-+-*-+-*\n" +
                "+-+-+-+-+-+-*-+-*\n" +
                "+-+-+-+-+-+-+-*-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+");
    }

    @Test
    void test3() {
        String actions = "G7 F3 G6 C3 D3 D2 D5 C7 F5 B5 C6 B6 D7 C8 E6 G4 F7 F9 E8 E9 D9 D8 H8 F8 H9 D4 E4 E3 H5 H4 J6 C5 J4 D6 E7 H3 E5 J3 F4 C6 G8 C9 PASS PASS";
        testBoard(actions, "+-+-o-+-o-o-+-*-+\n" +
                "+-+-o-o-*-o-*-*-+\n" +
                "+-+-o-*-*-*-*-+-+\n" +
                "+-o-o-o-*-+-*-+-*\n" +
                "+-o-o-*-*-*-+-*-+\n" +
                "+-+-+-o-*-*-o-o-*\n" +
                "+-+-o-+-o-o-+-o-o\n" +
                "+-+-+-o-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+");
    }
}
