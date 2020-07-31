package PointCalculator;

import Board.Intersection;
import Player.Color;
import PointCalculator.visitor.EncircledAreaVisitor;
import PointCalculator.visitor.VisitableEncirliedArea;

import java.util.List;

public interface EncircledArea extends VisitableEncirliedArea {
    public List<Intersection> getFullBorder();
    public List<Intersection> getMinimalBorder();
    public List<Intersection> getRingContent();
    public List<Intersection> getFullContent();
    public Color getBorderColor();
    public void addChildren(EncircledArea area);
    public List<EncircledArea> getChildrens();
}
