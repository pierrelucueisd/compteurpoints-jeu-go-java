package PointCalculator.EncirledAreaInterfaceComparator;

import Board.Intersection;
import PointCalculator.EncircledArea;

import java.util.Comparator;
import java.util.Optional;


public class EncirleAreaComparator {

    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    public static boolean isMinleftOfAreaAisafterBMinLeft(EncircledArea areaA, EncircledArea areaB) {
        Optional<Intersection> optMinLeftA = areaA.getFullContent().stream().min(comparatorX);
        Optional<Intersection> optMinLeftB = areaB.getFullContent().stream().min(comparatorX);
        if(!optMinLeftA.isPresent()) return true;
        else if(!optMinLeftB.isPresent()) return false;

        int minXA = optMinLeftA.get().getX();
        int minXB = optMinLeftB.get().getX();

        return minXB <= minXA;
    }
}
