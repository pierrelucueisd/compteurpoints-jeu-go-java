public class Main {

    public static void main(String[] args) {

        int boardSize = 9;
        GameController gc = new GameController(boardSize);
        if (args.length > 1) {
            gc.startGame(args[1]);
        } else {
            String sb = "You need to provide a file containing the players actions in input\n" +
                    "All actions must be separated by a space\n" +
                    "EXAMPLE: \"A1 B4 C5 PASS D8\"";
            System.out.println(sb);
        }
    }
}
