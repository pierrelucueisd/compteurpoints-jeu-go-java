package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Comparator.EncirleAreaComparator;
import PointCalculator.EncircledArea.Validator.EncircledAreaValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EncircledAreaStructurator {

    private Board b;

    public EncircledAreaStructurator(Board b, EncircledAreaValidator areaNotToBigValidator) {
        this.b = b;
    }

    public List<EncircledArea> structurateElementsOfList(List<EncircledArea> flatList) {
        List<EncircledArea> workingList = new ArrayList<>();
        workingList.addAll(flatList);
        structurateWorkingList(workingList);
        return getRootsOfEncircledArea(workingList);
    }

    private List<EncircledArea> getRootsOfEncircledArea(List<EncircledArea> workingList) {
        List<EncircledArea> rootElements = new ArrayList<>();
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
}
