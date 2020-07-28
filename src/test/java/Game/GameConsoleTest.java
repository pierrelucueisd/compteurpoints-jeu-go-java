package Game;

import Action.Action;
import Action.PassAction;
import Action.PlayOnBoardAction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameConsoleTest {
    GameConsole gc = new GameConsole();

    @Test
    void readPassAction() {
        Optional<Action> action = gc.readAction("pass");

        if (action.isPresent())
            assertTrue(action.get() instanceof PassAction);
        else
            fail();
    }

    @Test
    void readPlayAction() {
        Optional<Action> action = gc.readAction("D3");

        if (action.isPresent())
            assertTrue(action.get() instanceof PlayOnBoardAction);
        else
            fail();
    }

    @Test
    void readInvalidAction() {
        Optional<Action> action = gc.readAction("blablabla");

        if (action.isPresent())
            fail();
    }

}