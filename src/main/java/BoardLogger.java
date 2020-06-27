import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardLogger {
    private List<Board> history = new ArrayList<>();

    public void addBoard(Board b) {
        history.add(b);
    }

    public Optional<Board> getLastBoard() {
        if(history.isEmpty()) return  Optional.empty();
        else {
            return Optional.of(history.get(history.size()-1));
        }
    }

}
