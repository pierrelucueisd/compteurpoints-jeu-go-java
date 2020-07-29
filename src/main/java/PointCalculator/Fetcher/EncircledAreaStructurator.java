package PointCalculator.Fetcher;

import Board.Intersection;
import PointCalculator.EncircledArea;

import java.util.Comparator;

public class EncircledAreaStructurator {

    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);
    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    boolean isAreaAisInAreaB(EncircledArea areaA, EncircledArea AreaB) {
        return false;

    }
}
