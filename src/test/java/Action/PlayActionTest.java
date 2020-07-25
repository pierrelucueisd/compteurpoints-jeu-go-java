package Action;

import Board.BoardControllerMock;
import Board.Position;
import Player.Player;
import Player.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayActionTest {

    @Test
    void execute() {
        Position pos = new Position(0, 0);
        Action action = new PlayAction(pos);
        Player p = new Player(Color.Black);
        BoardControllerMock bc = new BoardControllerMock(9);

        // In this case we assume that this action is valid
        action.execute(bc, p);

        Color color = bc.getOccupationColorAtPosition(pos);
        assertEquals(color, p.getColor());
    }
}