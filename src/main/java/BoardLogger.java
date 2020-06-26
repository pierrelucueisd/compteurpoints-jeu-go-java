import java.util.ArrayList;
import java.util.List;

public class BoardLogger {
    private List<Board> history = new ArrayList<>();

    public void addBoard(Board b) {

    }

    public Board getLastBoard() {
        return new Board(9);
    }

}
