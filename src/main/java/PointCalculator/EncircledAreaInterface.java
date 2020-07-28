package PointCalculator;

import Board.Intersection;
import Player.Color;

import java.util.List;

public interface EncircledAreaInterface {
    public List<Intersection> getFullBorder();
    public List<Intersection> getRingContent();
    public List<Intersection> getFullContent();
    public Color getBorderColor();
}
