import java.util.Optional;

public class PassAction implements Action {

    public void execute(BoardController bc, Player p) { p.pass(); }

    public Optional<ErrorType> isAllowed(BoardController bc, Player p) {
        return Optional.empty();
    }
}
