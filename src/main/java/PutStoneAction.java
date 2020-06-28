import java.util.Optional;

public class PutStoneAction implements Action {
    private final Position position;

    public PutStoneAction(Position position) {
        this.position = position;
    }

    public ActionType getType() {
        return ActionType.Play;
    }

    public Optional<Position> getPosition() {
        return Optional.of(position);
    }

    public void execute(Board b, Player p) {
        b.putStone(p.getColor(), position);
    }
}
