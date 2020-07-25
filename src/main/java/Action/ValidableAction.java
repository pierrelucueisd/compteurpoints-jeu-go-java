package Action;

import Board.BoardController;
import Game.ErrorObservable;
import Game.ErrorType;
import Player.Player;

import java.util.Optional;

public abstract class ValidableAction implements Action {
    Action action;

    public ValidableAction(Action action) {
        this.action = action;
    }

    public abstract Optional<ErrorType> validate(BoardController bc, Player p);

    @Override
    public void execute(BoardController bc, Player p) {
        Optional<ErrorType> err = validate(bc, p);
        if(err.isPresent())
            ErrorObservable.getSingleton().notifyObservers(err.get());
        else
            action.execute(bc, p);
    }
}
