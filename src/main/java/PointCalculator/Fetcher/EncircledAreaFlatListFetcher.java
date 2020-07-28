package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;

import java.util.List;
import java.util.stream.Collectors;

public class EncircledAreaFlatListFetcher {
    private Board b;

    public EncircledAreaFlatListFetcher(Board b) {
        this.b = b;
    }

    public List<EncircledArea> fetchFlatListFromBoard() {
        List<Intersection> intersectionsVidesATraiter =  b.getAllIntersections().stream().filter(
                intersection -> !intersection.getOccupation().isPresent()
        ).collect(Collectors.toList());

        int jj = 0;
        jj = 8;
        return null;
    }
}
