package Board;

import Player.Color;

import java.util.List;

public interface IBoard {
    Integer getSize();
    Intersection getIntersection(Position pos);
    Intersection getIntersection(int x, int y);
    List<Intersection> getAllIntersections();
    void putStone(Color color, Position p);
    boolean isGroupPrisoner(List<Intersection> group);
    List<Intersection> getStoneGroup(Intersection i);
    String toString();
    boolean equals(Object o);
    int hashCode();
}
