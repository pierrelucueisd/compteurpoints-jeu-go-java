import java.util.Optional;
import java.util.Scanner;

public class GameConsole {

    Scanner scaner;

    public GameConsole(Scanner sc){
        this.scaner = sc;
    }

    public Action promptAction(Player p) {
        Optional<Position> pos = promptPosition();
        if(pos.isPresent()) return new Action(ActionType.Play, pos);
        else return new Action(ActionType.Pass, pos);
    }

    public Optional<Position> promptPosition() {
        return Optional.empty();
    }

    public static void printBoard(String board) {

    }

    public static void printResultError(ErrorType type) {

    }

    public static void printWinner(Player p) {

    }
}
