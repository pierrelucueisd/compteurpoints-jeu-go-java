package Action;

import Board.BoardController;
import Player.Player;

public class PassAction implements Action {
    public void execute(BoardController bc, Player p) {
        p.pass();
    }
}
