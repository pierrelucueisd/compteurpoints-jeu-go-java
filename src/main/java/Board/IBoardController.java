package Board;

import Player.Color;
import Player.Player;

public interface IBoardController {
    IBoard getCurrentBoard();

    void putStoneOnBoard(Color color, Position position);

    boolean isPositionValid(Position position);

    boolean isIntersectionVacant(Position position);

    boolean isActionSuicide(Position pos, Player p);

    boolean isActionKo(Position pos, Player p);

}
