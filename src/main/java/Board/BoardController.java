package Board;

import Player.Color;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardController implements IBoardController {
    private final List<Board> history = new ArrayList<>();
    private final Board current;

    public BoardController(int size) {
        this.current = new Board(size);
        saveCurrentBoard();
    }

    @Override
    public Board getCurrentBoard() {
        return current;
    }

    @Override
    public void putStoneOnBoard(Color color, Position position) {
        current.putStone(color, position);
        saveCurrentBoard();
    }

    @Override
    public boolean isPositionValid(Position position) {
        return position.isValid(current);
    }

    @Override
    public boolean isIntersectionVacant(Position position) {
        Intersection i = current.getIntersection(position);
        return i.isVacant();
    }

    @Override
    public boolean isActionSuicide(Position pos, Player p) {
        Board copy = new Board(current);
        copy.putStone(p.getColor(), pos);
        Intersection i = copy.getIntersection(pos);
        List<Intersection> group = copy.getStoneGroup(i);
        return copy.isGroupPrisoner(group);
    }

    @Override
    public boolean isActionKo(Position pos, Player p) {
        Board copy = new Board(current);
        copy.putStone(p.getColor(), pos);
        return getSecondLastBoard().map(value -> value.equals(copy)).orElse(false);
    }

    private void saveCurrentBoard() {
        history.add(new Board(current));
    }

    private Optional<Board> getSecondLastBoard() {
        return history.size() < 2 ? Optional.empty() : Optional.of(history.get(history.size() - 2));
    }

}
