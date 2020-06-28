import java.util.Optional;

public interface Action {
    ActionType getType();
    Optional<Position> getPosition();
    void execute(Board b, Player p);
}
