import java.util.Optional;

public class PutStoneAction implements Action {
    private final ActionType type;
    private final Optional<Position> position;

    public PutStoneAction(ActionType type, Optional<Position> position) {
        this.position = position;
        this.type = type;
    }

    public ActionType getType() {
        return ActionType.Play;
    }

    public Optional<Position> getPosition() {
        return position;
    }

    public void execute(Board b, Player p) {
        b.putStone(p.getColor(), position.get());
    }
}
