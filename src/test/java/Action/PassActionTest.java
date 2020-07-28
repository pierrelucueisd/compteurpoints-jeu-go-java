package Action;

import Board.BoardController;
import Player.Player;
import Player.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassActionTest {

    @Test
    void execute() {
        Player p = new Player(Color.Black);
        Action a = new PassAction();
        a.execute(new BoardController(9), p);

        assertTrue(p.hasPassed());
    }
}