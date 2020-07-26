package Action;

import Board.BoardController;
import Player.Player;

public interface Action {
    void execute(BoardController bc, Player p);
}
