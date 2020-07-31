package PointCalculator.visitor;

import PointCalculator.EncircledArea.EncircledArea;

public interface EncircledAreaVisitor {
    public void visit(EncircledArea area);
    public int getBlackPlayerPoints();
    public int getWhitePlayerPoints();
}
