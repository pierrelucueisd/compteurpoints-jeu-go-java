package Action;

import Board.IBoardController;
import Board.Position;
import Game.ErrorObservable;
import Game.ErrorType;
import Player.Player;

import java.util.Optional;

public class PlayOnBoardAction extends ValidableAction {

    Position position;

    public PlayOnBoardAction(Action action, Position position, ErrorObservable observable) {
        super(action, observable);
        this.position = position;
    }

    @Override
    public Optional<ErrorType> validate(IBoardController bc, Player p) {
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
}
