package PointCalculator.Fetcher.EncircledAreaValidator;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;
import PointCalculator.EncirledAreaInterfaceComparator.EncirleAreaComparator;

import java.util.ArrayList;
import java.util.List;

public class EncircledAreaValidator implements EncircledAreaValidatorInterface {

    private Board b;

    public EncircledAreaValidator(Board b) {
        this.b = b;
    }

    public boolean isRootValidated (EncircledArea area) {
        return !isAdjacentOfAllBoardSides(area) &&
                !isToBigAndContentTouchingBoardBorder(area);
    }

    protected boolean isToBigAndContentTouchingBoardBorder(EncircledArea area) {
        List<Intersection> minimalMembers = new ArrayList<Intersection>();
        minimalMembers.addAll(area.getMinimalBorder());
        minimalMembers.addAll(area.getFullContent());
        int minX = EncirleAreaComparator.getMinX(area.getFullContent(), b);
        int minY = EncirleAreaComparator.getMinY(area.getFullContent(), b);
        int maxX = EncirleAreaComparator.getMaxX(area.getFullContent(), b);
        int maxY = EncirleAreaComparator.getMaxY(area.getFullContent(), b);
        boolean isTouching = (minX == 0 || minY == 0 || maxX == b.getSize()-1 || maxY == b.getSize()-1);
        boolean isTooBig = minimalMembers.size() >  (b.getSize()*b.getSize()/2);
        return isTouching && isTooBig;
    }

    /*protected boolean isMinimalAreaSizeIsOverBoardHalfSize() {
    }*/

    protected boolean isAdjacentOfAllBoardSides(EncircledArea area) {
        List<Intersection> content = area.getFullContent();
        int tailleBoard = b.getSize();
        if(content.size() == 0) return false;
        int maxX = EncirleAreaComparator.getMaxX(content, b);
        int minX = EncirleAreaComparator.getMinX(content, b);
        int minY = EncirleAreaComparator.getMinY(content, b);
        int maxY = EncirleAreaComparator.getMaxY(content, b);

        return maxX == tailleBoard-1
                && minX == 0
                && minY == 0
                && maxY == tailleBoard-1;
    }
}
