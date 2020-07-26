package PointCalculator;

import Board.Board;
import Board.Intersection;

import java.util.List;

public class EncircledAreaFetcher {
    List<Intersection> contexte;
    Board b;
    public EncircledAreaFetcher(Board b) {
        this.contexte = b.getAllIntersections();
        this.b = b;
    }

    public void fetch() {
        Intersection i = b.getIntersection(3, 4);

        i.getOccupation();
    }

}
