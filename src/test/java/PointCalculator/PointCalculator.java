package PointCalculator;

import Board.Board;
import Board.Intersection;
import Player.Color;
import PointCalculator.visitor.EncircledAreaVisitor;
import PointCalculator.visitor.PointCalculatorVisitor;

import java.util.List;
import java.util.Optional;

public class PointCalculator {

    private Board b;
    List<EncircledArea> rootsAreas;
    private EncircledAreaVisitor pointCalculatorVisitor;
    private int blackPoints = 0;
    private int whitePoints = 0;

    public PointCalculator(Board b, List<EncircledArea> rootsAreas, EncircledAreaVisitor pointCalculatorVisitor) {
        this.pointCalculatorVisitor = pointCalculatorVisitor;
        this.b = b;
        this.rootsAreas = rootsAreas;
    }

    public void calculate() {
        addChildZonePointsToPlayersPoints(rootsAreas);

        List<Intersection> noMansIntersectionCloud = getIntersectionsCloudNotPossesedByEncircling();
        int nbCaseNoires   = countColorInIntersectionList(Color.Black, noMansIntersectionCloud);
        int nbCaseBlanches = countColorInIntersectionList(Color.White, noMansIntersectionCloud);
        blackPoints += nbCaseNoires;
        whitePoints += nbCaseBlanches;
    }

    public int getBlackPoints() {
        return blackPoints;
    }

    public int getWhitePoints() {
        return whitePoints;
    }

    private int countColorInIntersectionList(Color color, List<Intersection> list) {
        return (int) list.stream().filter(intersection -> {
            Optional<Color> optPlayerColor = intersection.getOccupation();
            if(!optPlayerColor.isPresent()) return false;
            return optPlayerColor.get() == color;
        }).count();
    }

    private List<Intersection> getIntersectionsCloudNotPossesedByEncircling() {
        List<Intersection> notOwnedArea = b.getAllIntersections();
        for(EncircledArea area : rootsAreas) {
            notOwnedArea.removeAll(area.getFullBorder());
            notOwnedArea.removeAll(area.getFullContent());
        }
        return notOwnedArea;
    }

    private void addChildZonePointsToPlayersPoints(List<EncircledArea> rootsAreas) {
        for(EncircledArea area: rootsAreas) {
            pointCalculatorVisitor.visit(area);
        }
        this.blackPoints += pointCalculatorVisitor.getBlackPlayerPoints();
        this.whitePoints += pointCalculatorVisitor.getWhitePlayerPoints();
    }

}
