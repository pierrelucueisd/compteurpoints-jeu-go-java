package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Player.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EncircledAreaFetcher {
    List<Intersection> contexte;
    Board b;
    public EncircledAreaFetcher(Board b) {
        this.contexte = b.getAllIntersections();
        this.b = b;
    }

    public void fetch() {
        List<List<Intersection>> groupesAdjacencesVides = new ArrayList<>();
        List<Intersection> intersectionsVidesATraiter =  contexte.stream().filter(
                intersection -> !intersection.getOccupation().isPresent()
        ).collect(Collectors.toList());

        Intersection i = b.getIntersection(3, 4);
        List<Intersection> anneauContenu = getAnneauInterieur(i, Color.White);
        int jj = 0;
        jj = 8;
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
