import java.util.Optional;

public class PutStoneAction implements Action {
    private final Optional<Position> position;

    public PutStoneAction(Optional<Position> position) {
        this.position = position;
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
