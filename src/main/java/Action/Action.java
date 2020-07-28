package Action;

import Board.IBoardController;
import Player.Player;

public interface Action {
    void execute(IBoardController bc, Player p);
}
