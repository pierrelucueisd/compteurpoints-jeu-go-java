package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Validator.EncircledAreaValidator;
import PointCalculator.EncircledArea.Validator.RootValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

class EncircledAreaFlatListFetcher {
    private Board b;
    private EncircledAreaValidator validator;

    public EncircledAreaFlatListFetcher(Board b) {
        this.b = b;
        this.validator = new RootValidator(b);
    }

    public List<EncircledArea> fetchFlatListFromBoard() {
        List<EncircledArea> areas = new ArrayList<>();
        EncircledAreaFetcher areaFecther = new EncircledAreaFetcherImplem(b);
        Stack<Intersection> aTraiter = getEmptyBoardIntersectionsATraiter();
        while(!aTraiter.isEmpty()) {
            Intersection inter = aTraiter.pop();
            Optional<EncircledArea> optArea = areaFecther.fetchAreaFromIntersection(inter);
            if(optArea.isPresent()) {
                if(validator.test(optArea.get())) areas.add(optArea.get());
                aTraiter.removeAll(optArea.get().getRingContent());
            }else aTraiter.remove(inter);
        }
        generateStickyEncerclingInList(areas);
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

    private void generateStickyEncerclingInList(List<EncircledArea> flatlist) {
        EncircledAreaFetcherImplem fetcher = new EncircledAreaFetcherImplem(b);
        ArrayList<EncircledArea> topAeraGenerated = new ArrayList<>();
        for(EncircledArea area : flatlist) {
            topAeraGenerated.addAll(fetcher.fetchTopStickyEncirledFlatList(area));
        }
        flatlist.addAll(topAeraGenerated);
    }
}
