public class Action {
    private final ActionType type;
    private ErrorType error;
    private final Position position;

    public Action(ActionType type, Position position) {
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
            case Play:
                b.putStone(p.getColor(), position);
        }
    }

    public boolean isAllowed(Board b, Player p) {
        if(!b.isPositionValid(position))
            error = ErrorType.InvalidPosition;
        if (!b.isIntersectionVacant(position))
            error =  ErrorType.IntersectionTaken;
        if(b.isSuicide(position, p.getColor()))
            error = ErrorType.Suicide;
        if(b.isKo(position, p.getColor()))
            error =  ErrorType.Ko;
        return error == ErrorType.None;
    }
}
