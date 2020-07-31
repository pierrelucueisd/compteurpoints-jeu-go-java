package PointCalculator.EncircledArea.Comparator;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea.EncircledArea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class EncirleAreaComparator {

    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);
    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);

    public static boolean isAreaAisInAreaB(EncircledArea areaA, EncircledArea AreaB, Board b) {
        ArrayList<Integer> offsets = new ArrayList<>();
        Integer BottomOffset = getMinY(areaA.getFullBorder(), b) - getMinY(AreaB.getFullBorder(), b);
        Integer leftOffset   = getMinX(areaA.getFullBorder(), b) - getMinX(AreaB.getFullBorder(), b);
        Integer TopOffset    = getMaxY(AreaB.getFullBorder(), b) - getMaxY(areaA.getFullBorder(), b);
        Integer RighttOffset = getMaxX(AreaB.getFullBorder(), b) - getMaxX(areaA.getFullBorder(), b);
        offsets.add(BottomOffset);
        offsets.add(leftOffset);
        offsets.add(TopOffset);
        offsets.add(RighttOffset);
        if(!offsets.stream().allMatch(offset -> offset >= 0)) return false;
        if(offsets.stream().allMatch(offset -> offset == 0))  return false;
        return true;
    }

    public static boolean isCloudIntersectionAisInCloudIntersectionB(
            List<Intersection> cloudA, List<Intersection> cloudB, Board b
    ) {
        ArrayList<Integer> offsets = new ArrayList<>();
        Integer BottomOffset = getMinY(cloudA, b) - getMinY(cloudB, b);
        Integer leftOffset   = getMinX(cloudA, b) - getMinX(cloudB, b);
        Integer TopOffset    = getMaxY(cloudB, b) - getMaxY(cloudA, b);
        Integer RighttOffset = getMaxX(cloudB, b) - getMaxX(cloudA, b);
        offsets.add(BottomOffset);
        offsets.add(leftOffset);
        offsets.add(TopOffset);
        offsets.add(RighttOffset);
        if(!offsets.stream().allMatch(offset -> offset >= 0)) return false;
        if(offsets.stream().allMatch(offset -> offset == 0))  return false;
        return true;
    }

    public static boolean isCommonContentIsNoMansLand(EncircledArea areaA, EncircledArea areaB) {
        return areaA.getFullContent().containsAll(areaB.getFullBorder())
                && areaB.getFullContent().containsAll(areaA.getFullBorder())
                && areaB.getFullContent().size() == areaA.getFullContent().size();
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

    public static Integer getMinX(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().min(comparatorX);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }

    public static Integer getMaxX(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().max(comparatorX);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getX();
    }

    public static Integer getMinY(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().min(comparatorY);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getY();
    }

    public static Integer getMaxY(List<Intersection> intersections, Board b) {
        Optional<Intersection> OptLeft = intersections.stream().max(comparatorY);
        if(!OptLeft.isPresent()) return -1;
        return OptLeft.get().getY();
    }
}
