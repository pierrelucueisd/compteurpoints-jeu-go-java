import Board.Board;
import Board.Builder.BoardBuilderForTests;
import Game.DualScannerMock;
import Game.GameController;
import PointCalculator.BoardPointCalculator;
import PointCalculator.BoardPointCalculatorImpl;
import PointCalculator.EncircledArea.Validator.RootValidator;
import PointCalculator.EncircledArea.Validator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    void testBoard(String input, String expected) {
        GameController gc = new GameController(9);
        gc.startGame(new DualScannerMock(input));
        String result = gc.getBoardToString(true);
        assertEquals(result, expected);
    }

    void testScore(String input, int black, int white) {
        GameController gc = new GameController(9);
        gc.startGame(new DualScannerMock(input));
        Board b = BoardBuilderForTests.buildBoard(gc.getBoardToString(false), 9);
        BoardPointCalculator calculator = new BoardPointCalculatorImpl(
                b,
                new RootValidator(b),
                new TakableValidatorNaive(),
                new TakableValidatorNaive());
        PlayersScoreStats stats = calculator.calculate();
        assertEquals(stats.getBlackPoints(), black, "Wrong scoring for black player");
        assertEquals(stats.getWhitePoints(), white, "Wrong scoring for white player");
    }

    String inputBuilder(String filename, String input) throws FileNotFoundException{
        File file = new File("src/test/files/" + filename);
        StringBuilder s = new StringBuilder();
        if(file.exists()){
            s.append(new Scanner(file).nextLine());
        }else{
            System.out.println("File can't be found");
        }
        s.append(input);
        return s.toString();
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


    @Test
    void calculateNaive_CasBasique_exempleInterfacePublique() {
        String representation =
                "+-+-○-+-+-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-●-●-●-●-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-○-+-○-●-+-●-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-●-●-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n";
        Board b = BoardBuilderForTests.buildBoard(representation, 15);
        BoardPointCalculator boardPointsCalculator = new BoardPointCalculatorImpl(
                b,
                new RootValidator(b),
                new TakableValidatorNaive(),
                new TakableValidatorNaive()
        );
        PlayersScoreStats playersScoreStats = boardPointsCalculator.calculate();
        int whitePoints = playersScoreStats.getWhitePoints();
        int blackPoints = playersScoreStats.getBlackPoints();
        assertEquals(17, blackPoints, "Problème de décompte de points de noir");
        assertEquals(17+9+8+3+26, whitePoints, "Problème de décompte de points avec Blanc");
    }

    @Test
    void playGoWithConsoleOnly(){
        String input = "D7 D6 C6 D5 E6 E5 F5 E4 D4 PASS E3 PASS F4 PASS C5";
        testBoard(input, "  A B C D E F G H J\n"+
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-+-○-+-+-+-+-+\n" +
                "6 +-+-○-+-○-+-+-+-+\n" +
                "5 +-+-○-+-+-○-+-+-+\n" +
                "4 +-+-+-○-+-○-+-+-+\n" +
                "3 +-+-+-+-○-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
        testScore(input, 12, 0);
    }

    @Test
    void playGoWithIncompleteFile() throws FileNotFoundException{
        String console = " F5 E9 PASS PASS";
        String input = inputBuilder("6.txt", console);

        testBoard(input, "  A B C D E F G H J\n"+
                "9 +-+-+-+-○-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 ○-+-+-+-+-+-+-+-+\n" +
                "6 +-○-+-+-+-+-+-+-+\n" +
                "5 +-+-○-+-+-●-+-+-+\n" +
                "4 +-○-+-+-+-+-+-+-+\n" +
                "3 ○-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
        testScore(input, 10, 1);
    }

    @Test
    void playGoWithConsoleEmptyMatch(){
        String input = "PASS PASS";

        testBoard(input, "  A B C D E F G H J\n"+
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-+-+-+-+-+-+-+\n" +
                "6 +-+-+-+-+-+-+-+-+\n" +
                "5 +-+-+-+-+-+-+-+-+\n" +
                "4 +-+-+-+-+-+-+-+-+\n" +
                "3 +-+-+-+-+-+-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
        testScore(input, 0, 0);
    }

    @Test
    void playGoWithFileAndConsoleEyes() throws FileNotFoundException {
        String console = " PASS PASS";
        String input = inputBuilder("7.txt", console);
        testBoard(input, "  A B C D E F G H J\n"+
                "9 +-+-+-+-+-+-+-+-+\n" +
                "8 +-+-+-+-+-+-+-+-+\n" +
                "7 +-+-●-○-○-○-+-+-+\n" +
                "6 +-○-○-+-+-+-○-+-+\n" +
                "5 +-○-○-+-○-+-○-+-+\n" +
                "4 +-○-○-+-+-+-○-+-+\n" +
                "3 +-+-+-○-○-○-+-+-+\n" +
                "2 +-+-+-+-+-+-+-+-+\n" +
                "1 +-+-+-+-+-+-+-+-+\n");
        testScore(input, 24, 1);
    }
}
