import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        int boardSize = 9;

        GameController gc = new GameController(
                boardSize,
                new GameConsole(new Scanner(System.in),
                new PositionDeserializer(boardSize))
        );
        gc.startGame();
    }
}
