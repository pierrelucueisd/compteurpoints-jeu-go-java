import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int boardSize = 9;
        GameController gc = new GameController(boardSize);

        Scanner scanner;
        if (args.length > 0)
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                scanner = new Scanner(System.in);
            }
        else
            scanner = new Scanner(System.in);

        gc.startGame(scanner);
    }
}
