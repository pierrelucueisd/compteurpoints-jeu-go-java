public class Action {
    private final ActionType type;
    private ErrorType error;
    private final Position position;

    public Action(ActionType type, Position position) {
        this.position = position;
        this.error = ErrorType.None;
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    public ErrorType getError() {
        return error;
    }

    public void execute(Board b, Player p) {
        switch (type) {
            case Pass:
                break;
            case Play:
                b.putStone(p.getColor(), position);
                break;
        }
    }

    public boolean isAllowed(Board b, Player p) {
        if(type == ActionType.Pass)
            return true;
        else if(type == ActionType.Invalid || !b.isPositionValid(position))
            error = ErrorType.InvalidPosition;
        else if (!b.isIntersectionVacant(position))
            error =  ErrorType.IntersectionTaken;
        else if(b.isSuicide(position, p.getColor()))
            error = ErrorType.Suicide;
        else if(b.isKo(position, p.getColor()))
            error =  ErrorType.Ko;

        return error == ErrorType.None;
    }
}
