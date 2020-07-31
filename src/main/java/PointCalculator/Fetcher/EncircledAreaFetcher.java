package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Player.Color;
import PointCalculator.EncircledArea;
import PointCalculator.EncirledAreaInterfaceComparator.EncirleAreaComparator;
import PointCalculator.EncircledAreaImplem;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EncircledAreaFetcher {
    private Board b;

    public EncircledAreaFetcher(Board b) {
        this.b = b;
    }

    public Optional<EncircledArea> fetchAreaFromIntersection(Intersection i) {
        EncircledArea areaWhite = fetchColorAreaFromIntersection(i, Color.White);
        EncircledArea areaBlack = fetchColorAreaFromIntersection(i, Color.Black);
        if( EncirleAreaComparator.isCommonContentIsNoMansLand(areaBlack, areaWhite)) return Optional.empty();
        EncircledArea retainedArea = retainCorrectArea(areaWhite, areaBlack, i);
        List<Intersection> retainedRing = areaWhite.getRingContent().stream().filter(intersection -> {
            return areaBlack.getRingContent().contains(intersection) && !intersection.getOccupation().isPresent();
        }).collect(Collectors.toList());
        return Optional.of(
            new EncircledAreaImplem(
                retainedArea.getFullBorder(),
                retainedArea.getMinimalBorder(),
                retainedRing,
                retainedArea.getFullContent(),
                retainedArea.getBorderColor()
            )
        );
    }

    private EncircledArea retainCorrectArea(EncircledArea areaA, EncircledArea AreaB, Intersection i){
        boolean isIInClaudA = isIntersectionBetwenAreaBorder(i, areaA);
        boolean isIInClaudB = isIntersectionBetwenAreaBorder(i, AreaB);
        if(isIInClaudA && ! isIInClaudB) return areaA;
        else if(isIInClaudB && ! isIInClaudA) return AreaB;
        if(areaA.getFullContent().size() < AreaB.getFullContent().size()) return areaA;
        else return AreaB;
    }

    private boolean isIntersectionBetwenAreaBorder(Intersection i, EncircledArea area) {
        List<Intersection> intersection = new ArrayList<Intersection>();
        intersection.add(i);
        return EncirleAreaComparator.isCloudIntersectionAisInCloudIntersectionB(
                intersection, area.getFullBorder(), b
        );
    }



    protected EncircledArea fetchTopStickyEncirler(EncircledArea area) {
        EncircledArea topArea = area;
        Optional<EncircledArea> fetchedArea = fetchFirstAscendantStickyEncercling(area, true    );
        while(fetchedArea.isPresent()) {
            topArea = fetchedArea.get();
            fetchedArea = fetchFirstAscendantStickyEncercling(topArea, true);
        }
        return topArea;
    }

    protected List<EncircledArea> fetchTopStickyEncirledFlatList(EncircledArea area) {
        List<EncircledArea> result = new ArrayList<>();
        Optional<EncircledArea> optFetchedArea = fetchFirstAscendantStickyEncercling(area, false);
        while(optFetchedArea.isPresent()) {
            EncircledArea fetchedArea = optFetchedArea.get();
            result.add(fetchedArea);
            optFetchedArea = fetchFirstAscendantStickyEncercling(fetchedArea, false);
        }
        return result;
    }

    protected Optional<EncircledArea> fetchFirstAscendantStickyEncercling(EncircledArea area, boolean addChild) {
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
        boolean isBorder = bordureHypothetique.stream().allMatch(intersection -> {
            return intersection.getOccupation().isPresent() && intersection.getOccupation().get() == firstBorderColor;
        });
        if(!isBorder) return Optional.empty();
        EncircledArea topArea = new EncircledAreaImplem(
                fetcher.fetchFullBorder(),
                fetcher.fetchExternalMinimalBorder(),
                new ArrayList<Intersection>(),
                fullContentHypothetique,
                firstBorderColor
        );
        if(addChild) topArea.addChildren(area);
        return Optional.of(topArea);
    }

    protected EncircledArea fetchColorAreaFromIntersection(Intersection i, Color borderColor) {
        List<Intersection> contenuAnneau = getAnneauInterieur(i, borderColor);
        BorderFetcher fetcher = new BorderFetcher(b, contenuAnneau);
        List<Intersection> fullBorder = fetcher.fetchFullBorder();
        List<Intersection> minimalBorder = fetcher.fetchExternalMinimalBorder();
        List<Intersection> fullContent = getAdjacencesTransitives(i, b, intersection -> {
            return !fullBorder.contains(intersection);
        });
        EncircledArea area = new EncircledAreaImplem(fullBorder, minimalBorder, contenuAnneau, fullContent, borderColor);
        return area;
    }

    protected List<Intersection> getAnneauInterieur(Intersection i, Color borderColor) {
        return getAdjacencesTransitives(i, b,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != borderColor
        );
    }

    // obtient les adjacences transitives satifaisant toutes le prédicat
    protected static List<Intersection> getAdjacencesTransitives(
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
