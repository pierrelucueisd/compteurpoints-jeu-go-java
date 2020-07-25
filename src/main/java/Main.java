import Game.GameController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int boardSize = 9;
        GameController gc = new GameController(boardSize);

        if (args.length > 0)
            try {
                Scanner scanner = new Scanner(new File(args[0]));
                gc.startGame(scanner);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        else
            System.out.println("Provide a valid file path as parameter");

    }
}
