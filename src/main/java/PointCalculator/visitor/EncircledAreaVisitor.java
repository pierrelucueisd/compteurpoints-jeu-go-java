package PointCalculator.visitor;

import Board.Intersection;
import PointCalculator.EncircledArea.EncircledArea;

import java.util.List;

public interface EncircledAreaVisitor {
    public void visit(EncircledArea area);
    List<Intersection> getIntersectionsTraitees();
    public int getBlackPlayerPoints();
    public int getWhitePlayerPoints();
}
