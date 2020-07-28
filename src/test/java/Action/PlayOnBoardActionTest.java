package Action;

import Board.BoardControllerMock;
import Board.Position;
import Game.ErrorObservable;
import Game.ErrorObserverMock;
import Game.ErrorType;
import Player.Player;
import Player.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlayOnBoardActionTest {

    BoardControllerMock bc;
    Player player;
    PlayOnBoardAction action;
    ErrorObservable observable = ErrorObservable.getSingleton();
    ErrorObserverMock obs = new ErrorObserverMock();

    public PlayOnBoardActionTest() {
        observable.attach(obs);
    }

    @BeforeEach
    void setUp() {
        Position pos = new Position(0, 0);
        player = new Player(Color.Black);
        action = new PlayOnBoardAction(new PlayAction(pos), pos);
        bc = new BoardControllerMock(9);
    }

    @Test
    void validateWithInvalidPosition() {
        bc.setCurrentError(ErrorType.InvalidPosition);

        Optional<ErrorType> error = action.validate(bc, player);
        assertError(error, ErrorType.InvalidPosition);
    }

    @Test
    void validateWithSuicide() {
        bc.setCurrentError(ErrorType.Suicide);

        Optional<ErrorType> error = action.validate(bc, player);
        assertError(error, ErrorType.Suicide);
    }

    @Test
    void validateWithKo() {
        bc.setCurrentError(ErrorType.Ko);

        Optional<ErrorType> error = action.validate(bc, player);
        assertError(error, ErrorType.Ko);
    }

    @Test
    void validateWithIntersectionTaken() {
        bc.setCurrentError(ErrorType.IntersectionTaken);

        Optional<ErrorType> error = action.validate(bc, player);
        assertError(error, ErrorType.IntersectionTaken);
    }

    @Test
    void validateWithNoError() {
        Optional<ErrorType> error = action.validate(bc, player);
        if (error.isPresent())
            fail();
    }

    @Test
    void executeWithError() {
        bc.setCurrentError(ErrorType.Suicide);

        action.execute(bc, player);

        assertEquals(ErrorType.Suicide, obs.currentError);
    }

    @Test
    void executeWithNoError() {
        action.execute(bc, player);

        assertNull(obs.currentError);
    }

    private void assertError(Optional<ErrorType> error, ErrorType expected) {
        if (error.isPresent())
            assertEquals(error.get(), expected);
        else
            fail();
    }
}