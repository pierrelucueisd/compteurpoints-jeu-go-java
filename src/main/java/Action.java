import java.util.Optional;

public interface Action {
    Optional<ErrorType> isAllowed(GameController gc, Player p);
    Optional<Position> getPosition();
    void execute(GameController gc, Player p);
}
