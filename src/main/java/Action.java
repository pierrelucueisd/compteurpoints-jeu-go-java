import java.util.Optional;

public class Action {
    private final ActionType type;
    private ErrorType error;
    private final Optional<Position> position;
    private GameController gameControler;

    public Action(ActionType type, Optional<Position> position, GameController gC) {
        this.position = position;
        this.error = ErrorType.None;
        this.type = type;
        this.gameControler = gC;
    }

    public ActionType getType() {
        return type;
    }

    public ErrorType getError() {
        return error;
    }

    public void execute(Player p) {
        switch (type) {
            case Pass:
                break;
            case Play:
                Board board = gameControler.getBoard();
                board.putStone(p.getColor(), position.get());
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
