package PointCalculator;

import Board.Intersection;
import Player.Color;
import PointCalculator.visitor.EncircledAreaVisitor;

import java.util.ArrayList;
import java.util.List;

public class EncircledAreaImplem implements EncircledArea {

    private List<Intersection> fullBorder;
    private List<Intersection> ringContent;
    private List<Intersection> fullContent;
    private List<Intersection> minimalBorder;
    private Color borderColor;
    private List<EncircledArea> childrens = new ArrayList<>();

        public EncircledAreaImplem(
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


    public List<EncircledArea> getChildrens() {
        return childrens;
    }

    public void addChildren(EncircledArea child) {
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

    @Override
    public void accept(EncircledAreaVisitor visitor) {
        visitor.visit(this);
    }
}
