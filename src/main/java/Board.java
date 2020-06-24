import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {
    private final Integer size;
    private final Intersection[][] intersections;
    private final BoardLogger logger = new BoardLogger();

    public Board(Integer size) {
        this.size = size;
        intersections = new Intersection[size][size];
        for(int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                intersections[y][x] = new Intersection(new Position(x,y));

    }

    public boolean isPositionValid(Position pos) {
        return pos.getX() < size && pos.getY() < size;
    }

    public boolean isIntersectionVacant(Position pos) {
        return getIntersection(pos).isVacant();
    }

    public void putStone(Color color, Position pos) {
        getIntersection(pos).setStone(color);
        // Get other colors thant the one played
        Color[] otherColors = Arrays.stream(Color.values())
                .filter(c -> c != color)
                .toArray(Color[]::new);
        for (Color c: otherColors)
                removePrisoners(pos, c);
    }

    public boolean isSuicide(Position pos, Color color) {
        List<Intersection> group = getStoneGroup(pos, color, Color.values());
        return groupHasNoLiberty(group);
    }

    public boolean isKo(Position pos, Color c) {
        return false;
    }

    public void removeDeadStone() { }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Intersection i = getIntersection(new Position(x, y));
                board.append(i.isVacant() ? "+" : i.getStone().getSymbol());
                board.append((x == size - 1) ? "\n" : "-");
            }
        }
        return board.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(size, board.size) &&
                Arrays.deepEquals(intersections, board.intersections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, intersections);
    }

    private boolean groupHasNoLiberty(List<Intersection> group) {
        return false;
    }

    private List<Intersection> getStoneGroup(Position pos, Color color, Color[] values) {
        return null;
    }

    private void removePrisoners(Position pos, Color color) {
        List<Intersection> group = getStoneGroup(pos, color, Color.values());
        //if (groupHasNoLiberty(group))
            // do something
    }

    private Intersection getIntersection(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return intersections[x][y];
    }
}
