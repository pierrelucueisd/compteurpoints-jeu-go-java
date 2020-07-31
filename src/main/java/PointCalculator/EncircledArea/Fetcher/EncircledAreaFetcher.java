package PointCalculator.EncircledArea.Fetcher;

import Board.Intersection;
import PointCalculator.EncircledArea.EncircledArea;

import java.util.Optional;

public interface EncircledAreaFetcher {
    public Optional<EncircledArea> fetchAreaFromIntersection(Intersection i);
}
