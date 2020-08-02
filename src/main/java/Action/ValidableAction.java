package Action;

import Board.IBoardController;
import Game.ErrorObservable;
import Game.ErrorType;
import Player.Player;

import java.util.Optional;

public abstract class ValidableAction implements Action {
    Action action;
    ErrorObservable observable;

    public ValidableAction(Action action, ErrorObservable observable) {
        this.action = action;
        this.observable = observable;
    }

    public abstract Optional<ErrorType> validate(IBoardController bc, Player p);

    @Override
    public void execute(IBoardController bc, Player p) {
        Optional<ErrorType> err = validate(bc, p);
        if(err.isPresent())
            observable.notifyObservers(err.get());
        else
            action.execute(bc, p);
    }
}
