package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Player.Color;
import PointCalculator.EncirledAreaInterfaceComparator.EncirleAreaComparator;
import PointCalculator.EncircledArea;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EncircledAreaFetcher {
    private Board b;

    public EncircledAreaFetcher(Board b) {
        this.b = b;
    }

    protected EncircledArea fetchAreaFromIntersection(Intersection i) {
        EncircledArea areaWhite = fetchColorAreaFromIntersection(i, Color.White);
        EncircledArea areaBlack = fetchColorAreaFromIntersection(i, Color.Black);
        EncircledArea retainedArea;
        if(EncirleAreaComparator.isMinleftOfAreaAisafterBMinLeft(areaWhite, areaBlack)) retainedArea = areaWhite;
        else retainedArea = areaBlack;
        List<Intersection> retainedRing = areaWhite.getRingContent().stream().filter(intersection -> {
            return areaBlack.getRingContent().contains(intersection);
        }).collect(Collectors.toList());
        return new EncircledArea(
                retainedArea.getFullBorder(),
                retainedRing,
                retainedArea.getFullContent(),
                retainedArea.getBorderColor()
        );
    }

    protected EncircledArea fetchTopStickyEncirler(EncircledArea area) {
        EncircledArea topArea = area;
        Optional<EncircledArea> fetchedArea = fetchFirstAscendantStickyEncercling(area);
        while(fetchedArea.isPresent()) {
            topArea = fetchedArea.get();
            fetchedArea = fetchFirstAscendantStickyEncercling(topArea);
        }
        return topArea;
    }

    protected Optional<EncircledArea> fetchFirstAscendantStickyEncercling(EncircledArea area) {
        ArrayList<Intersection> fullContentHypothetique = new ArrayList<Intersection>();
        fullContentHypothetique.addAll(area.getFullBorder());
        fullContentHypothetique.addAll(area.getFullContent());
        BorderFetcher fetcher = new BorderFetcher(b, fullContentHypothetique);
        fetcher.fetchExternalMinimalBorder();
        List<Intersection> bordureHypothetique = fetcher.fetchExternalMinimalBorder();
        if(bordureHypothetique.isEmpty()) return Optional.empty();
        Optional<Color> firstOccupation = bordureHypothetique.get(0).getOccupation();
        if(!firstOccupation.isPresent()) return Optional.empty();
        Color firstBorderColor = firstOccupation.get();
        bordureHypothetique.stream().allMatch(intersection -> {
            return intersection.getOccupation().isPresent() && intersection.getOccupation().get() == firstBorderColor;
        });
        EncircledArea topArea = new EncircledArea(
                fetcher.fetchFullBorder(),
                new ArrayList<Intersection>(),
                fullContentHypothetique,
                firstBorderColor
        );
        topArea.addChildren(area);
        return Optional.of(topArea);
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
