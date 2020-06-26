import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int boardSize = 9;
        GameController gc = new GameController(boardSize);
        if (args.length > 0) {
            Scanner scanner = new Scanner(new File(args[0]));
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                gc.startGame(input);
            } else
                printInstruction();
        } else
            printInstruction();
    }

    private static void printInstruction() {
        String s = "You need to provide a file containing the players actions in input\n" +
                "All actions must be separated by a space\n" +
                "EXAMPLE: \"A1 B4 C5 PASS D8\"";
        System.out.println(s);
    }
}
