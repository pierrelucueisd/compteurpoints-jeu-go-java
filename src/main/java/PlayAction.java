import java.util.Optional;

public class PlayAction implements Action {
    private final Position position;

    public PlayAction(Position position) {
        this.position = position;
    }

    @Override
    public Optional<ErrorType> isAllowed(BoardController bc, Player p) {
        if(!bc.isPositionValid(position))
            return Optional.of(ErrorType.InvalidPosition);
        if (!bc.isIntersectionVacant(position))
            return Optional.of(ErrorType.IntersectionTaken);
        if(bc.isActionSuicide(position, p))
            return Optional.of(ErrorType.Suicide);
        if(bc.isActionKo(position, p))
            return Optional.of(ErrorType.Ko);
        return Optional.empty();
    }

    public void execute(BoardController bc, Player p) {
        bc.putStoneOnBoard(p.getColor(), position);
    }
}
