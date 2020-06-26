import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
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
        return pos.getX() < size && pos.getY() < size &&
                pos.getX() >=0 && pos.getY()>= 0;
    }

    public boolean isIntersectionVacant(Position pos) {
        Optional<Intersection> inter = getIntersection(pos);
        if(!inter.isPresent() || !inter.get().isVacant()) return false;
        else return true;
    }

    public void putStone(Color color, Position pos) {
        getIntersection(pos).get().setOccupation(Optional.of(color)); // @todo Atenttion le code ici n'est pas robuste
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

    private Optional<Intersection> getIntersection(Position pos) {
        Intersection intersect =  Objects.requireNonNull(intersections.stream()
                .filter(i -> i.hasPosition(pos))
                .findFirst()
                .orElse(null));
        if(intersect == null) return Optional.empty();
        else return Optional.of(intersect);
    }

    private Optional<Intersection> getLeftIntersectionOf(Intersection intersection) {
        Position pos = intersection.getPosition();
        Position leftPos = new Position(pos.getX()-1, pos.getY());
        return getIntersection(leftPos);
    }

    private Optional<Intersection> getTopIntersectionOf(Intersection intersection) {
        Position pos = intersection.getPosition();
        Position leftPos = new Position(pos.getX(), pos.getY()+1);
        return getIntersection(leftPos);
    }

    private Optional<Intersection> getRightIntersectionOf(Intersection intersection) {
        Position pos = intersection.getPosition();
        Position leftPos = new Position(pos.getX()+1, pos.getY());
        return getIntersection(leftPos);
    }

    private Optional<Intersection> getBottomIntersectionOf(Intersection intersection) {
        Position pos = intersection.getPosition();
        Position leftPos = new Position(pos.getX(), pos.getY()-1);
        return getIntersection(leftPos);
    }

    private List<Intersection> getAdjacencyOf(Intersection intersec) {
        List<Intersection> intersections = new ArrayList<Intersection>();
        Optional<Intersection> inter;
        inter = getLeftIntersectionOf(intersec);
        if(inter.isPresent()) intersections.add(inter.get());

        inter = getTopIntersectionOf(intersec);
        if(inter.isPresent()) intersections.add(inter.get());

        inter = getRightIntersectionOf(intersec);
        if(inter.isPresent()) intersections.add(inter.get());

        inter = getBottomIntersectionOf(intersec);
        if(inter.isPresent()) intersections.add(inter.get());
        return intersections;
    }



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
}
