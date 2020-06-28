import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardController {
    private final List<Board> history = new ArrayList<>();

    public void addBoard(Board b) {
        history.add(b);
    }

    public Optional<Board> getLastBoard() {
        if (history.isEmpty())
            return Optional.empty();
        else
            return Optional.of(history.get(history.size()-1));
    }

    public boolean isActionSuicide(Action a, Board b, Player p) {
        Board bC = new Board(b);
        if (a.getPosition().isPresent()) {
            b.putStone(p.getColor(), a.getPosition().get());
            return bC.isSuicide(a.getPosition().get());
        }
        return false;
    }

    public boolean isActionKo(Action a, Board b, Player p) {
        Board bC = new Board(b);
        a.getPosition().ifPresent((pos -> b.putStone(p.getColor(), pos)));
        if (a.getPosition().isPresent()) {
            b.putStone(p.getColor(), a.getPosition().get());
            return getLastBoard().map(value -> value.equals(bC)).orElse(false);
        }
        return false;
    }

}
