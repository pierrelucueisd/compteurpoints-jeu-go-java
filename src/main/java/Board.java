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
        Intersection intersection = getIntersection(pos).get(); //@todo Atenttion le code ici n'est pas robuste
        intersection.setOccupation(Optional.of(color));

        List<Intersection> adjacences = getAdjacencyOf(intersection);
        ArrayList<Color> otherColors = getOtherColor(color);
        for(Intersection inter : adjacences) {
            //System.out.println(inter.getPosition().toString()); @todo enlever une fois termnié
            if(inter.getOccupation().isPresent() && otherColors.contains(inter.getOccupation().get())) {
                removePrisoners(inter); //remove seulement si groupe sans liberté
            }
        }
    }

    public ArrayList<Color> getOtherColor(Color color) {
        return Arrays.stream(Color.values())
                .filter(c -> c != color)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isSuicide(Position pos, Color color) {
        List<Intersection> group = getStoneGroup(pos, color);
        return isGroupSuroundedByOneColor(group);
    }

    public boolean isKo(Position pos, Color c) {
        return false;
    }

    public void removeDeadStone() { }


    private boolean intersectionHasLiberty(Intersection inter) {
        for(Intersection adj : getAdjacencyOf(inter)) {
            Optional<Color> vacant = Optional.empty();
            if(adj.getOccupation() == vacant) return true;
        }
        return false;
    }

    private boolean isGroupSuroundedByOneColor(List<Intersection> group) { //is surrondedByAnotherplayer
        List<Color> BorderColor = new ArrayList<>();
        if(group.size() > 1 && group.get(1).getOccupation().isPresent()) {
            Color color = group.get(1).getOccupation().get();
            for(Intersection borderInter : getGroupBorder(group)) {
                if(!borderInter.getOccupation().isPresent()) return false;
                Color borderColor = borderInter.getOccupation().get();
                if(color != borderColor && !BorderColor.contains(borderColor)) BorderColor.add(borderColor);
            }
        }
        return BorderColor.size()==1;
    }

    private List<Intersection> getGroupBorder(List<Intersection> group) {
        List<Intersection> border = new ArrayList<>();
        List<Intersection> adjacencesGlobales = new ArrayList<>();
        for(Intersection inter: group) {
            for(Intersection adj : getAdjacencyOf(inter)) {
                adjacencesGlobales.add(adj);
            }
        }
        for(Intersection adj: adjacencesGlobales) {
            if(!group.contains(adj)) border.add(adj);
        }
        return border;
    }

    private List<Intersection> getStoneGroup(Position pos, Color color) {
        Optional<Intersection> interPot = getIntersection(pos);
        List<Intersection> group = new ArrayList<>();
        if(interPot.isPresent()){
            Intersection inter = interPot.get();
            List<Optional<Color>> occupations = new ArrayList<>();
            occupations.add(Optional.of(color));
            group = getConnectedIntersectionWithOccupationsOf(inter, occupations);
        }
        return group;
    }

    private void removePrisoners(Intersection inter) {
        if(inter.getOccupation().isPresent()) {
            Color color = inter.getOccupation().get();
            List<Intersection> group = getStoneGroup(inter.getPosition(), color);
            if (isGroupSuroundedByOneColor(group)){
                Optional<Color> vacant = Optional.empty();
                setIntersectionsOccupancy(group, vacant);
            }
        }
    }

    private void setIntersectionsOccupancy(List<Intersection> intersections, Optional<Color> occupation) {
        for(Intersection inter : intersections) {
            inter.setOccupation(occupation);
        }
    }

    private List<Intersection> getConnectedIntersectionWithOccupationsOf(
            Intersection interStart, List<Optional<Color>> occupations) {
        List<Intersection> visited = new ArrayList<>();
        Stack<Intersection> stackATraiter = new Stack<>();
        List<Intersection> conected = new ArrayList<>();

        stackATraiter.push(interStart);
        do  {
            Intersection aTraiter = stackATraiter.pop();
            visited.add(aTraiter);
            if(occupations.contains(interStart.getOccupation())){
                conected.add(aTraiter);
                for(Intersection inter : getAdjacencyOf(aTraiter))
                    if(!visited.contains(inter)) stackATraiter.push(inter);
            }
        }while (!stackATraiter.empty());

        return conected;
    }

    private Optional<Intersection> getIntersection(Position pos) {
        if(!isPositionValid(pos)) return Optional.empty();
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
