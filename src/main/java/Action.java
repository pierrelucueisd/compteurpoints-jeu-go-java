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

    public ErrorType getError() {
        return error;
    }

    public void execute(Board b, Player p) {
        switch (type) {
            case Pass:
                p.pass();
                break;
            case Play:
                b.putStone(p.getColor(), position.get());
                break;
        }
    }

    public boolean isAllowed(Board b, Player p) {
        if(position.isPresent()) {
            if(!b.isPositionValid(position.get()))
                error = ErrorType.InvalidPosition;
            if (!b.isIntersectionVacant(position.get()))
                error =  ErrorType.IntersectionTaken;
            if(b.isSuicide(position.get(), p.getColor()))
                error = ErrorType.Suicide;
            if(b.isKo(position.get(), p.getColor()))
                error =  ErrorType.Ko;
        }

        return error == ErrorType.None;
    }
}
