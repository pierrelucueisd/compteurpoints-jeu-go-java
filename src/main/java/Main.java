import Game.DualScanner;
import Game.GameController;

public class Main {

    public static void main(String[] args) {
        int boardSize = 9;
        GameController gc = new GameController(boardSize);
        gc.startGame(new DualScanner(args[1]));
    }
}
