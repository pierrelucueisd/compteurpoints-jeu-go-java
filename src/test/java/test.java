import Game.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    GameController gc;

    @BeforeEach
    void setUp() {
        int boardSize = 9;
        gc = new GameController(boardSize);
    }

    void testBoard(String input, String expected) {
        Scanner scanner = new Scanner(input);
        gc.startGame(scanner);
        String result = gc.getBoardToString();
        assertEquals(result, expected);
    }

    @Test
    void test1() {
        String actions = "G7 G6 F6 G5 H5 H6 J6 H7 H8";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-○-+\n" +
                "7 +-+-+-+-+-+-○-●-+\n" +
                "6 +-+-+-+-+-○-●-●-○\n" +
                "5 +-+-+-+-+-+-●-○-+\n" +
                "4 +-+-+-+-+-+-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }

    @Test
    void test2() {
        String actions = "G7 H7 J7 H6 H8 PASS G6 PASS J6 PASS H5";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-○-+\n" +
                "7 +-+-+-+-+-+-○-+-○\n" +
                "6 +-+-+-+-+-+-○-+-○\n" +
                "5 +-+-+-+-+-+-+-○-+\n" +
                "4 +-+-+-+-+-+-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }

    @Test
    void test3() {
        String actions = "G7 F3 G6 C3 D3 D2 D5 C7 F5 B5 C6 B6 D7 C8 E6 G4 F7 F9 E8 E9 D9 D8 H8 F8 H9 D4 E4 E3 H5 H4 J6 C5 J4 D6 E7 H3 E5 J3 F4 C6 G8 C9 PASS PASS";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-●-+-●-●-+-○-+\n" +
                "8 +-+-●-●-○-●-○-○-+\n" +
                "7 +-+-●-○-○-○-○-+-+\n" +
                "6 +-●-●-●-○-+-○-+-○\n" +
                "5 +-●-●-○-○-○-+-○-+\n" +
                "4 +-+-+-●-○-○-●-●-○\n" +
                "3 +-+-●-+-●-●-+-●-●\n" +
                "2 +-+-+-●-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }
    @Test

    void testBoardIsKo() {
        String actions = "E4 D4 D5 C5 F5 D6 E6 E5 D5 PASS PASS";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-+-+-+-+-+-+-+\n" +
                "6 +-+-+-●-○-+-+-+-+\n" +
                "5 +-+-●-+-●-○-+-+-+\n" +
                "4 +-+-+-●-○-+-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }

    @Test
    void testBoardIsNotKo() {
        String actions = "D4 D5 E4 F4 E6 F6 C5 E5 D6 G5 F5 E5 PASS PASS";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-+-+-+-+-+-+-+\n" +
                "6 +-+-+-○-○-●-+-+-+\n" +
                "5 +-+-○-+-●-+-●-+-+\n" +
                "4 +-+-+-○-○-●-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }
    @Test
    void testBoardIsSuicide() {
        String actions = "F6 F5 F4 F7 E4 E7 D5 G6 E6 D7 G5 H5 G7 E5 PASS PASS";
        testBoard(actions,
                "  A B C D E F G H J\n" +
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-+-●-●-●-○-+-+\n" +
                "6 +-+-+-+-○-○-●-+-+\n" +
                "5 +-+-+-○-+-●-○-●-+\n" +
                "4 +-+-+-+-○-○-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }

    //Stone has no liberty but then captures a group a stone.
    @Test
    void testBoardIsNotSuicide() {
        String actions = "F6 F5 F4 F7 E4 E7 D5 G6 E6 D7 G5 H5 G7 D6 F8 E5 PASS PASS";
        testBoard(actions,
                "  A B C D E F G H J\n"+
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-○-+-+-+\n" +
                "7 +-+-+-●-●-●-○-+-+\n" +
                "6 +-+-+-●-+-+-●-+-+\n" +
                "5 +-+-+-○-●-●-○-●-+\n" +
                "4 +-+-+-+-○-○-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
    }
}
