import java.util.Optional;

public interface Action {
    public ActionType getType();
    public Optional<Position> getPosition();
    public void execute(Board b, Player p);
}
