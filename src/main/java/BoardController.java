import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardController {
    private final List<Board> history = new ArrayList<>();

    public void addBoard(Board b) {
        history.add(new Board(b));
    }

    public Optional<Board> getSecondLastBoard() {
        return history.size() < 2 ? Optional.empty() : Optional.of(history.get(history.size() - 2));
    }

    public boolean isActionSuicide(Action a, Board b, Player p) {
        Board copy = new Board(b);
        if (a.getPosition().isPresent()) {
            copy.putStone(p.getColor(), a.getPosition().get());
            return copy.isSuicide(a.getPosition().get());
        }
        return false;
    }

    public boolean isActionKo(Action a, Board b, Player p) {
        Board copy = new Board(b);
        if (a.getPosition().isPresent()) {
            copy.putStone(p.getColor(), a.getPosition().get());
            return getSecondLastBoard().map(value -> value.equals(copy)).orElse(false);
        }
        return false;
    }

}
