package Action;

import Board.IBoardController;
import Game.ErrorObservable;
import Game.ErrorType;
import Player.Player;

import java.util.Optional;

public abstract class ValidableAction implements Action {
    Action action;

    public ValidableAction(Action action) {
        this.action = action;
    }

    public abstract Optional<ErrorType> validate(IBoardController bc, Player p);

    @Override
    public void execute(IBoardController bc, Player p) {
        Optional<ErrorType> err = validate(bc, p);
        if(err.isPresent())
            ErrorObservable.getSingleton().notifyObservers(err.get());
        else
            action.execute(bc, p);
    }
}
