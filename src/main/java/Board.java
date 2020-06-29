import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Responsability : Updates the intersections of a board by putting/removing stones.
 */

public class Board {
    private final Integer size;
    private final Intersection[][] intersections;

    public Board(Integer size) {
        this.size = size;
        intersections = new Intersection[size][size];
        for(int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                intersections[y][x] = new Intersection(new Position(x,y));
    }

    public Board(Board b) {
        this.size = b.size;
        this.intersections = new Intersection[b.size][b.size];
        for(int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                this.intersections[y][x] = new Intersection(b.intersections[y][x]);
    }

    public Integer getSize() {
        return size;
    }

    public Intersection getIntersection(Position pos) {
        return getIntersection(pos.getX(), pos.getY());
    }

    public Intersection getIntersection(int x, int y) {
        return intersections[y][x];
    }

    public boolean isIntersectionVacant(Position pos) {
        return getIntersection(pos).isVacant();
    }

    public void putStone(Color color, Position p) {
        Intersection i = getIntersection(p);
        i.setOccupation(color);
        removePrisoners(i);
    }

    public boolean isSuicide(Position pos) {
        Intersection i = getIntersection(pos);
        List<Intersection> group = getStoneGroup(i);
        return isGroupPrisoner(group);
    }

    private void removePrisoners(Intersection i) {
        List<Intersection> enemyNeighbors = i.getEnemyNeighbors(this);
        for (Intersection enemy: enemyNeighbors)  {
            List<Intersection> group = getStoneGroup(enemy);
            if (isGroupPrisoner(group))
                group.forEach(v -> v.setOccupation(null));
        }
    }

    private boolean isGroupPrisoner(List<Intersection> group) {
        return group.stream().noneMatch(i -> i.hasLiberty(this));
    }

    private List<Intersection> getStoneGroup(Intersection i) {
        List<Intersection> visited = new ArrayList<>();
        Stack<Intersection> notVisited = new Stack<>();
        List<Intersection> group = new ArrayList<>();

        group.add(i);
        visited.add(i);
        notVisited.addAll(i.getFriendlyNeighbors(this));
        while (!notVisited.empty())  {
            Intersection current = notVisited.pop();
            if (i.isFriendly(current))
                group.add(current);

            visited.add(current);
            List<Intersection> currentNotVisitedNeighbors = current.getFriendlyNeighbors(this)
                    .stream()
                    .filter(n -> !visited.contains(n))
                    .collect(Collectors.toList());
            notVisited.addAll(currentNotVisitedNeighbors);
        }

        return group;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        String boardAxis = IntStream.rangeClosed('A', 'Z')
                .filter(c -> c != 'I').limit(size)
                .mapToObj(c -> " " + (char) c)
                .collect(Collectors.joining());

        board.append(" ").append(boardAxis).append('\n');

        for(int y = size - 1; y >= 0; y--) {
            board.append(y + 1);
            board.append(" ");
            for (int x = 0; x < size; x++) {
                Optional<Color> occupation = getIntersection(x, y).getOccupation();
                String symbol = occupation.isPresent() ? occupation.get().getSymbol() : "+";
                board.append(symbol);
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
}
