package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;
import PointCalculator.EncirledAreaInterfaceComparator.EncirleAreaComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EncircledAreaStructurator {

    private Board b;
    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);
    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    public EncircledAreaStructurator(Board b) {
        this.b = b;
    }

    public List<EncircledArea> structurateElementsOfList(List<EncircledArea> flatList) {
        List<EncircledArea> workingList = removeBoardFromList(flatList);
        List<EncircledArea> rootElements = new ArrayList<>();
        structurateWorkingList(workingList);
        for(EncircledArea area: workingList) {
           boolean isNotChildren = workingList.stream().allMatch(
                   wokingElem -> {
                       return area == wokingElem || !wokingElem.getChildrens().contains(area);
                   }
           );
           if(isNotChildren) rootElements.add(area);
        }
        return rootElements;
    }

    private void structurateWorkingList(List<EncircledArea> workingList) {
        for(EncircledArea area: workingList) {
            List<EncircledArea> parents = workingList.stream().filter(workElement -> {
                return EncirleAreaComparator.isCloudIntersectionAisInCloudIntersectionB(
                        area.getFullContent(), workElement.getFullContent(), b
                );
            }).collect(Collectors.toList());
            Optional<EncircledArea> optDirectParent = parents.stream().min((elem1, elem2) ->
                    Integer.compare(
                            elem1.getFullContent().size(),
                            elem2.getFullContent().size()
                    )
            );
            if(optDirectParent.isPresent()) {
                EncircledArea directParent = optDirectParent.get();
                directParent.addChildren(area);
            }
            int i = 0;
        }
    }

    private List<EncircledArea> removeBoardFromList(List<EncircledArea> flatList) {
        return flatList.stream().filter(encircledArea -> {
            return !isAdjacentOfAllBoardSides(encircledArea);
        }).collect(Collectors.toList());
    }

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
