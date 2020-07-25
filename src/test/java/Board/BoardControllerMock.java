package Board;

import Game.ErrorType;
import Player.Color;
import Player.Player;

public class BoardControllerMock implements IBoardController {
    Board board;
    ErrorType currentError;

    public BoardControllerMock(int size) {
        this.board = new Board(size);
    }

    public Color getOccupationColorAtPosition(Position pos) {
        return board.getIntersection(pos).getOccupation().orElse(null);
    }

    @Override
    public Board getCurrentBoard() {
        return board;
    }

    @Override
    public boolean isActionKo(Position pos, Player p) {
        return currentError == ErrorType.Ko;
    }

    @Override
    public boolean isActionSuicide(Position pos, Player p) {
        return currentError == ErrorType.Suicide;
    }

    @Override
    public boolean isIntersectionVacant(Position position) {
        return currentError != ErrorType.IntersectionTaken;
    }

    @Override
    public boolean isPositionValid(Position position) {
        return currentError != ErrorType.InvalidPosition;
    }

    @Override
    public void putStoneOnBoard(Color color, Position position) {
        board.putStone(color, position);
    }

    public void setCurrentError(ErrorType err) {
        currentError = err;
    }
}
