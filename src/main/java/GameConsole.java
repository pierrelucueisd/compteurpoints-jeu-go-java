public class GameConsole {

    public static Action promptAction(Player p) {
        return new Action(ActionType.Play, new Position());
    }

    public static Position promptPosition() {
        return null;
    }

    public static void printBoard(String board) {

    }

    public static void printResultError(ErrorType type) {

    }

    public static void printWinner(Player p) {

    }
}
