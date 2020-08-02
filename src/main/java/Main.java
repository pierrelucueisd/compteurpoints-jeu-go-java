import Board.LecteurEntree.LecteurEntree;
import Board.LecteurEntree.LecteurEntreeImpl;
import Game.GameController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int boardSize = 9;


        if (args.length > 0)
            try {
                LecteurEntree lecteur = new LecteurEntreeImpl(args[0]);
                GameController gc = new GameController(
                        boardSize,
                        lecteur
                );
                gc.startGame();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        else
            System.out.println("Provide a valid file path as parameter");

    }
}
