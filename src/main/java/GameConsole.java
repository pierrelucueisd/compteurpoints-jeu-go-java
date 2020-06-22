import java.util.List;

public class GameConsole {

    public static Action promptAction(Player p, ActionType[] actions) {
        return new Action(ActionType.Play);
    }

    public static Position promptPosition() {
        return null;
    }

    public static void printBoard(String board) {

    }

    public static void printResultError(ResultType type) {

    }

    public static void printWinner(Player p) {

    }
}
