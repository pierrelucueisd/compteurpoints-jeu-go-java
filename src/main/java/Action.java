import java.util.Optional;

public class Action {
    private final ActionType type;
    private final Optional<Position> position;

    public Action(ActionType type, Optional<Position> position) {
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
        switch (type) {
            case Pass:
                break;
            case Play:
                b.putStone(p.getColor(), position.get());
                break;
        }
    }
}
