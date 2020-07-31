package PointCalculator;

import Board.Intersection;
import Player.Color;
import PointCalculator.visitor.EncircledAreaVisitor;
import PointCalculator.visitor.VisitableEncirliedArea;

import java.util.List;

public interface EncircledAreaInterface extends VisitableEncirliedArea {
    public List<Intersection> getFullBorder();
    public List<Intersection> getRingContent();
    public List<Intersection> getFullContent();
    public Color getBorderColor();
}
