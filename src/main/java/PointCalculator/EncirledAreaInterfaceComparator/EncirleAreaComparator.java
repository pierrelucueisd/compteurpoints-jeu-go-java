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

    protected static Integer getMinX(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().min(comparatorX);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }

    protected static Integer getMaxX(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().max(comparatorX);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }

    protected static Integer getMaxY(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().max(comparatorY);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }

    protected static Integer getMinY(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().min(comparatorY);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }
}
