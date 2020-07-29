package PointCalculator.EncirledAreaInterfaceComparator;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class EncirleAreaComparator {

    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);

    public static boolean isAreaAisInAreaB(EncircledArea areaA, EncircledArea AreaB) {
        return false;

    }

    public static boolean isMinleftOfAreaAisafterBMinLeft(EncircledArea areaA, EncircledArea areaB) {
        Optional<Intersection> optMinLeftA = areaA.getFullContent().stream().min(comparatorX);
        Optional<Intersection> optMinLeftB = areaB.getFullContent().stream().min(comparatorX);
        if(!optMinLeftA.isPresent()) return true;
        else if(!optMinLeftB.isPresent()) return false;

        int minXA = optMinLeftA.get().getX();
        int minXB = optMinLeftB.get().getX();

        return minXB <= minXA;
    }

    public static int getMinLeft(List<Intersection> intersections, Board b) {
        return 0;
    }
}
