package PointCalculator;

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
        List<Intersection> anneauContenuNoir = getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        int jj = 0;
        jj = 8;

        /*while(!intersectionsVidesATraiter.isEmpty()) {
            Intersection intersection = intersectionsVidesATraiter.get(0);
            List<Intersection> voisins = intersection.getNeighbors(b);
            voisins.add(intersection);
            groupesAdjacencesVides.add(voisins);
            //intersectionsVidesATraiter.remove(intersection);
            intersectionsVidesATraiter.removeAll(voisins);
        }*/
    }

    // obtient les adjacences transitives satifaisant toutes le prédicat
    protected List<Intersection> getAdjacencesTransitives(Intersection i, Predicate<Intersection> predicate) {
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
