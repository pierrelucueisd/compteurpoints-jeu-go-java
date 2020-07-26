package Board;

import Player.Color;

import java.util.List;

public interface BoardInterface {
    public Integer getSize();
    public Intersection getIntersection(Position pos);
    public Intersection getIntersection(int x, int y);
    public void putStone(Color color, Position p);
    public boolean isGroupPrisoner(List<Intersection> group);
    public List<Intersection> getStoneGroup(Intersection i);
    public String toString();
    public boolean equals(Object o);
    public int hashCode();
}
