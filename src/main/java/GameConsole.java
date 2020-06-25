import javax.sql.rowset.serial.SerialException;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class GameConsole {

    Scanner scaner;
    PositionDeserializer deserializer;

    public GameConsole(Scanner sc, PositionDeserializer ds){
        this.scaner = sc;
        this.deserializer = ds;
    }

    public Action promptAction(Player p) throws Exception {
        String mot = scaner.nextLine();
        if(mot.toLowerCase().equals("pass")) return new Action(ActionType.Pass, Optional.empty());
        Optional<Position> pos = this.deserializer.deserialize(mot);
        if(pos.isPresent()) return new Action(ActionType.Play, pos);
        else throw new Exception("Attention le mot: \"" + mot + "\" est incorrect!");
    }

    public static void printBoard(String board) {

    }

    public static void printResultError(ErrorType type) {

    }

    public static void printWinner(Player p) {

    }
}
