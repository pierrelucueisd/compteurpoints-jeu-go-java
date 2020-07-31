package PointCalculator.visitor;

import Board.Intersection;
import Player.Color;
import PointCalculator.EncircledArea;
import PointCalculator.EncircledAreaInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointCalculatorVisitor implements EncircledAreaVisitor {

    int nbZonesVisites = 0;
    List<Intersection> intersectionsTraitees = new ArrayList<>();
    int blackPlayerPoints = 0;
    int whitePlayerPoints = 0;

    public int getBlackPlayerPoints() {
        return blackPlayerPoints;
    }

    public int getWhitePlayerPoints() {
        return whitePlayerPoints;
    }

    @Override
    public void visit(EncircledArea area) {
        for(EncircledAreaInterface child: area.getChildrens()) {
            child.accept(this);
        }
        List<Intersection> untreatedContent =  getUntreatedIntersectionsIn(area.getFullContent());
        List<Intersection> untreatedBorder = getUntreatedIntersectionsIn(area.getFullBorder());
        Color playerColor = area.getBorderColor();
        addPointsToPlayerColor(playerColor, untreatedBorder.size());
        addPointsToPlayerColor(playerColor, untreatedContent.size());
        nbZonesVisites++;
        intersectionsTraitees.addAll(untreatedContent);
        intersectionsTraitees.addAll(untreatedBorder);
    }



    private void addPointsToPlayerColor(Color playerColor, int points) {
        if(playerColor == Color.Black) blackPlayerPoints += points;
        else if(playerColor == Color.White) whitePlayerPoints += points;
    }

    private List<Intersection> getUntreatedIntersectionsIn(List<Intersection> list) {
        return list.stream().filter(intersection -> {
            return !intersectionsTraitees.contains(intersection);
        }).collect(Collectors.toList());
    }

}
