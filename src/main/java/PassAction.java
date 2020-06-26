import java.util.Optional;

public class PassAction implements Action {
    private final ActionType type;
    private final Optional<Position> position;

    public PassAction(ActionType type, Optional<Position> position) {
        this.position = position;
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    public Optional<Position> getPosition() {
        return position;
    }

    public void execute(Board b, Player p) {
    }
}
