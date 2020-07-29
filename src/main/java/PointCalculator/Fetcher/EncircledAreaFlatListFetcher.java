package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class EncircledAreaFlatListFetcher {
    private Board b;

    public EncircledAreaFlatListFetcher(Board b) {
        this.b = b;
    }

    public List<EncircledArea> fetchFlatListNoStickyFromBoard() {
        List<EncircledArea> areas = new ArrayList<>();
        EncircledAreaFetcher areaFecther = new EncircledAreaFetcher(b);
        Stack<Intersection> aTraiter = getEmptyBoardIntersectionsATraiter();
        while(!aTraiter.isEmpty()) {
            Intersection inter = aTraiter.pop();
            Optional<EncircledArea> optArea = areaFecther.fetchAreaFromIntersection(inter);
            if(optArea.isPresent()) {
                areas.add(optArea.get());
                aTraiter.removeAll(optArea.get().getRingContent());
            }else aTraiter.add(inter);
            //List<EncircledArea> stickyAreas = areaFecther.fetchTopStickyEncirledFlatList(area);
        }
        return areas;
    }

    private Stack<Intersection> getEmptyBoardIntersectionsATraiter() {
        Stack<Intersection> aTraiter = new Stack<Intersection>();
        aTraiter.addAll(
                b.getAllIntersections().stream().filter(
                        intersection -> !intersection.getOccupation().isPresent()
                ).collect(Collectors.toList())
        );
        return aTraiter;
    }
}
