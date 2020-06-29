import java.util.Optional;

public interface Action {
    Optional<ErrorType> isAllowed(BoardController bc, Player p);
    void execute(BoardController bc, Player p);
}
