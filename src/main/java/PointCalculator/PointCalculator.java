package PointCalculator;

import Board.Board;
import Board.Intersection;
import Player.Color;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.TakableValidatorNaive;
import PointCalculator.visitor.EncircledAreaVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointCalculator {

    private Board b;
    List<EncircledArea> rootsAreas;
    List<EncircledArea> calculatedRoorAreas = new ArrayList<EncircledArea>();
    private EncircledAreaVisitor pointCalculatorVisitor;
    private int blackPoints = 0;
    private int whitePoints = 0;
    private TakableValidatorNaive takableRootValidator;

    public PointCalculator(Board b,
                           List<EncircledArea> rootsAreas,
                           EncircledAreaVisitor pointCalculatorVisitor,
                           TakableValidatorNaive takableRootValidator
    ) {
        this.pointCalculatorVisitor = pointCalculatorVisitor;
        this.b = b;
        this.rootsAreas = rootsAreas;
        this.takableRootValidator = takableRootValidator;
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
        for(EncircledArea area : calculatedRoorAreas) {
            notOwnedArea.removeAll(area.getFullBorder());
            notOwnedArea.removeAll(area.getFullContent());
        }
        return notOwnedArea;
    }

    private void addChildZonePointsToPlayersPoints(List<EncircledArea> rootsAreas) {
        for(EncircledArea area: rootsAreas) {
            if(!takableRootValidator.isValid(area)) {
                pointCalculatorVisitor.visit(area);
                calculatedRoorAreas.add(area);
            }
        }
        this.blackPoints += pointCalculatorVisitor.getBlackPlayerPoints();
        this.whitePoints += pointCalculatorVisitor.getWhitePlayerPoints();
    }

}
