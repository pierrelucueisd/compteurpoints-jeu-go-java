package Action;

import Board.IBoardController;
import Player.Player;

public class PassAction implements Action {
    public void execute(IBoardController bc, Player p) {
        p.pass();
    }
}
