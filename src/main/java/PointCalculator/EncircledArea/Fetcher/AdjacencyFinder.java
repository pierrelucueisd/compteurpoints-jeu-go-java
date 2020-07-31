package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Intersection;
import Player.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;

final class AdjacencyFinder {
    // obtient les adjacences transitives satifaisant toutes le prédicat
    protected static List<Intersection> findFromPredicate(
            Intersection i, Board b, Predicate<Intersection> predicate
    ) {
        Stack<Intersection> aTraiter = new Stack<Intersection>();
        List<Intersection> traite = new ArrayList<Intersection>();
        List<Intersection> resultat = new ArrayList<Intersection>();
        aTraiter.push(i);
        while(!aTraiter.isEmpty()) {
            Intersection intersection = aTraiter.pop();
            if(!traite.contains(intersection)) {
                traite.add(intersection);
                Optional<Color> c = intersection.getOccupation();
                if(predicate.test(intersection)){
                    aTraiter.addAll(intersection.getNeighbors(b));
                    resultat.add(intersection);
                }
            }
        }
        return resultat;
    }
}
