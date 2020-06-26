import java.util.*;
import java.util.stream.Collectors;

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

    public boolean isPositionValid(Position pos) {
        return pos.getX() < size && pos.getY() < size &&
                pos.getX() >=0 && pos.getY()>= 0;
    }

    public boolean isIntersectionVacant(Position pos) {
        Optional<Intersection> inter = getIntersection(pos);
        return inter.isPresent() && inter.get().isVacant();
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


    /* précondition il faut tester sur la pierre jouré donc la case à position contient la pierre pour le moment
    * cette fonction ne fonctionne pas correctement*/
    public boolean isSuicide(Position pos, Color color) {
        Optional<Intersection> interPot = getIntersection(pos);
        if(!interPot.isPresent()) return false;
        Intersection intersection = interPot.get();
        List<Intersection> group = getConnectedIntersectionOfSameOccupation(intersection);
        return isGroupSurroundedByOneColor(group);
    }

    public boolean isKo(Position pos, Color c) {
        return false;
    }

    public void removeDeadStone() { }

    private boolean isGroupSurroundedByOneColor(List<Intersection> group) { //is surrondedByAnotherplayer
        List<Optional<Color>> groupOccupations = getGroupOccupations(group);
        List<Intersection> border = getGroupBorder(group);
        List<Optional<Color>> occupationBorder = getGroupOccupations(border);
        Optional<Color> vacant = Optional.empty();
        if(occupationBorder.size() != 1 || occupationBorder.contains(vacant)) return false;
        Optional<Color> occupationB = occupationBorder.get(0);
        if(groupOccupations.contains(occupationB)) return false;
        return true;
    }

    private List<Optional<Color>> getGroupOccupations(List<Intersection> group) {
        List<Optional<Color>> occupations = new ArrayList<>();
        for(Intersection inter : group) {
            Optional<Color> occupation = inter.getOccupation();
            if(!occupations.contains(occupation)) occupations.add(occupation);
        }
        return occupations;
    }

    private List<Intersection> getGroupBorder(List<Intersection> group) {
        List<Intersection> border = new ArrayList<>();
        List<Intersection> adjacencesGlobales = new ArrayList<>();
        for(Intersection inter: group) {
            adjacencesGlobales.addAll(getAdjacencyOf(inter));
        }
        for(Intersection adj: adjacencesGlobales) {
            if(!group.contains(adj)) border.add(adj);
        }
        return border;
    }

    private List<Intersection> getConnectedIntersectionOfSameOccupation(Intersection inter) {
        List<Optional<Color>> occupations = new ArrayList<>();
        occupations.add(inter.getOccupation());
        return getConnectedIntersectionWithOccupationsOf(inter, occupations);
    }

    private void removePrisoners(Intersection inter) {
        if(inter.getOccupation().isPresent()) {
            List<Intersection> group = getConnectedIntersectionOfSameOccupation(inter);
            if (isGroupSurroundedByOneColor(group)){
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
        return Optional.of(intersections[pos.getY()][pos.getX()]);
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

    private List<Intersection> getAdjacencyOf(Intersection i) {
        List<Intersection> intersections = new ArrayList<>();
        getLeftIntersectionOf(i).ifPresent(intersections::add);
        getTopIntersectionOf(i).ifPresent(intersections::add);
        getRightIntersectionOf(i).ifPresent(intersections::add);
        getBottomIntersectionOf(i).ifPresent(intersections::add);
        return intersections;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for(int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                Intersection i = getIntersection(new Position(x, y)).orElse(null);
                if (i != null) {
                    String symbol = i.getOccupation().orElse(Color.None).getSymbol();
                    board.append(symbol);
                    board.append((x == size - 1) ? "\n" : "-");
                }
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
