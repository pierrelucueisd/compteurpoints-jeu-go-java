import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardLogger {
    private List<Board> history = new ArrayList<>();

    public void addBoard(Board b) {
        history.add(b);
    }

    // @todo remplacer lorsque la fonction board.deepClone sera implémentée
    public Optional<Board> getLastBoard() {
        Board b = new Board(9);
        return Optional.of(b);
    }

}
