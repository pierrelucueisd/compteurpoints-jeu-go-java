package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Player.Color;
import PointCalculator.EncirledAreaInterfaceComparator.EncirleAreaComparator;
import PointCalculator.EncircledArea;

import java.util.*;
import java.util.function.Predicate;

public class EncircledAreaFetcher {
    private Board b;

    public EncircledAreaFetcher(Board b) {
        this.b = b;
    }

    protected EncircledArea fetchAreaFromIntersection(Intersection i) {
        EncircledArea areaWhite = fetchColorAreaFromIntersection(i, Color.White);
        EncircledArea areaBlack = fetchColorAreaFromIntersection(i, Color.Black);
        if(EncirleAreaComparator.isMinleftOfAreaAisafterBMinLeft(areaWhite, areaBlack)) return areaWhite;
        else return areaBlack;
    }

    protected EncircledArea fetchColorAreaFromIntersection(Intersection i, Color borderColor) {
        List<Intersection> contenuAnneau = getAnneauInterieur(i, borderColor);
        BorderFetcher fetcher = new BorderFetcher(b, contenuAnneau);
        List<Intersection> fullBorder = fetcher.fetchFullBorder();
        List<Intersection> fullContent = getAdjacencesTransitives(i, b, intersection -> {
            return !fullBorder.contains(intersection);
        });
        EncircledArea area = new EncircledArea(fullBorder, contenuAnneau, fullContent, borderColor);
        return area;
    }

    protected List<Intersection> getAnneauInterieur(Intersection i, Color borderColor) {
        return getAdjacencesTransitives(i, b,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != borderColor
        );
    }

    // obtient les adjacences transitives satifaisant toutes le prédicat
    protected static List<Intersection> getAdjacencesTransitives(Intersection i, Board b, Predicate<Intersection> predicate) {
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
