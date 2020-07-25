package Action;

import Board.IBoardController;
import Board.Position;
import Player.Player;

public class PlayAction implements Action {

    Position position;

    public PlayAction(Position position) {
        this.position = position;
    }

    @Override
    public void execute(IBoardController bc, Player p) {
        bc.putStoneOnBoard(p.getColor(), position);
    }
}
