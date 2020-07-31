package PointCalculator.visitor;

import Board.Intersection;
import Player.Color;
import PointCalculator.EncircledArea;
import PointCalculator.EncircledAreaInterface;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.TakableValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointCalculatorVisitor implements EncircledAreaVisitor {

    private int nbZonesVisites = 0;
    private List<Intersection> intersectionsTraitees = new ArrayList<>();
    private TakableValidator takableChildValidator;
    private PlayersScoreStats playersScoreStats;


    public PointCalculatorVisitor(TakableValidator takableChildValidator, PlayersScoreStats playersScoreStats) {
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
            if(!takableChildValidator.isValid(child))
                child.accept(this);
        }
        List<Intersection> untreatedContent =  getUntreatedIntersectionsIn(area.getFullContent());
        List<Intersection> untreatedBorder = getUntreatedIntersectionsIn(area.getFullBorder());
        Color playerColor = area.getBorderColor();
        playersScoreStats.addPlayerPoints(playerColor, untreatedBorder.size());
        playersScoreStats.addPlayerPoints(playerColor, untreatedContent.size());
        nbZonesVisites++;
        intersectionsTraitees.addAll(untreatedContent);
        intersectionsTraitees.addAll(untreatedBorder);
    }

    private List<Intersection> getUntreatedIntersectionsIn(List<Intersection> list) {
        return list.stream().filter(intersection -> {
            return !intersectionsTraitees.contains(intersection);
        }).collect(Collectors.toList());
    }

}
