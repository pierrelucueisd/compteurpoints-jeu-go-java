package PointCalculator.visitor;

import Board.Intersection;
import Player.Color;
import PointCalculator.EncircledArea;
import PointCalculator.EncircledAreaInterface;
import PointCalculator.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.PlayersStats.PlayersScoreStats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointCalculatorVisitor implements EncircledAreaVisitor {

    private int nbZonesVisites = 0;
    private List<Intersection> intersectionsTraitees = new ArrayList<>();
    private EncircledAreaValidator takableChildValidator;
    private PlayersScoreStats playersScoreStats;


    public PointCalculatorVisitor(EncircledAreaValidator takableChildValidator, PlayersScoreStats playersScoreStats) {
        this.takableChildValidator = takableChildValidator;
        this.playersScoreStats = playersScoreStats;
    }

    public int getBlackPlayerPoints() {
        return playersScoreStats.getBlackPoints();
    }

    public int getWhitePlayerPoints() {
        return playersScoreStats.getWhitePoints();
    }

    @Override
    public void visit(EncircledArea area) {
        for(EncircledAreaInterface child: area.getChildrens()) {
            if(!takableChildValidator.test(child))
                child.accept(this);
        }
        List<Intersection> untreatedContent =  getUntreatedIntersectionsIn(area.getFullContent());
        List<Intersection> untreatedBorder = getUntreatedIntersectionsIn(area.getFullBorder());
        Color playerColor = area.getBorderColor();
        playersScoreStats.addPlayerPoints(playerColor, untreatedBorder.size());
        playersScoreStats.addPlayerPoints(playerColor, untreatedContent.size());
        intersectionsTraitees.addAll(untreatedContent);
        intersectionsTraitees.addAll(untreatedBorder);
        nbZonesVisites++;
    }

    private List<Intersection> getUntreatedIntersectionsIn(List<Intersection> list) {
        return list.stream().filter(intersection -> {
            return !intersectionsTraitees.contains(intersection);
        }).collect(Collectors.toList());
    }

}
