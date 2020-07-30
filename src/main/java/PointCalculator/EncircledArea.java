package PointCalculator;

import Board.Intersection;
import Player.Color;
import PointCalculator.EncircledAreaInterface;

import java.util.ArrayList;
import java.util.List;

public class EncircledArea implements EncircledAreaInterface {

    private List<Intersection> fullBorder;
    private List<Intersection> ringContent;
    private List<Intersection> fullContent;
    private List<Intersection> minimalBorder;
    private Color borderColor;
    private List<EncircledAreaInterface> childrens = new ArrayList<>();

    public List<EncircledAreaInterface> getChildrens() {
        return childrens;
    }

    public EncircledArea(
            List<Intersection> fullBorder,
            List<Intersection> minimalBorder,
            List<Intersection> ringContent,
            List<Intersection> fullContent,
            Color borderColor
    ) {
        this.fullBorder = fullBorder;
        this.ringContent = ringContent;
        this.fullContent = fullContent;
        this.borderColor = borderColor;
        this.minimalBorder = minimalBorder;
    }

    public void addChildren(EncircledAreaInterface child) {
        childrens.add(child);
    }

    public List<Intersection> getMinimalBorder() {
        return minimalBorder;
    }

    public List<Intersection> getFullBorder() {
        return fullBorder;
    }

    public List<Intersection> getRingContent() {
        return ringContent;
    }

    public List<Intersection> getFullContent() {
        return fullContent;
    }

    public Color getBorderColor() {
        return borderColor;
    }
}
