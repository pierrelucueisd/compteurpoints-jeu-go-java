import java.util.Optional;

public class Action {
    private final ActionType type;
    private ErrorType error;
    private final Optional<Position> position;

    public Action(ActionType type, Optional<Position> position) {
        this.position = position;
        this.error = ErrorType.None;
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    public Optional<Position> getPosition() {
        return position;
    }

    public ErrorType getError() {
        return error;
    }

    public void execute(Board b, Player p) {
        switch (type) {
            case Pass:
                break;
            case Play:
                b.putStone(p.getColor(), position.get());
                break;
        }
    }
}
