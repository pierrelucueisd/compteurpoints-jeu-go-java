import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private final Integer size;
    private final List<Intersection> intersections;
    private final BoardLogger logger = new BoardLogger();

    public Board(Integer size) {
        this.size = size;
        intersections = initIntersections();
    }

    private List<Intersection> initIntersections() {
        return IntStream.range(0, size)
                .mapToObj(i -> IntStream.range(0, size)
                    .mapToObj(j -> new Position(i,j))
                    .map(Intersection::new))
                .flatMap(Function.identity())
                .collect(Collectors.toList());
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
        return "Board";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(size, board.size) &&
                Objects.equals(intersections, board.intersections);
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
        return Objects.requireNonNull(intersections.stream()
                .filter(i -> i.hasPosition(pos))
                .findFirst()
                .orElse(null));
    }
}
