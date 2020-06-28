import java.util.Optional;

public class PlayAction implements Action {
    private final Position position;

    public PlayAction(Position position) {
        this.position = position;
    }

    @Override
    public Optional<ErrorType> isAllowed(GameController gc, Player p) {
        Board b = gc.getBoard();
        BoardController bc = gc.getBoardController();
        if(!position.isValid(b))
            return Optional.of(ErrorType.InvalidPosition);
        if (!b.isIntersectionVacant(position))
            return Optional.of(ErrorType.IntersectionTaken);
        if(bc.isActionSuicide(this, b, p))
            return Optional.of(ErrorType.Suicide);
        if(bc.isActionKo(this, b, p))
            return Optional.of(ErrorType.Ko);
        return Optional.empty();
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.ofNullable(position);
    }

    public void execute(GameController gc, Player p) {
        Board b = gc.getBoard();
        b.putStone(p.getColor(), position);
        gc.getBoardController().addBoard(b);
    }

}
