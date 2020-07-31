package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import PointCalculator.EncircledArea;
import PointCalculator.Fetcher.EncircledAreaValidator.IsRootValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidatorInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class EncircledAreaFlatListFetcher {
    private Board b;
    private EncircledAreaValidatorInterface validator;

    public EncircledAreaFlatListFetcher(Board b) {
        this.b = b;
        this.validator = new IsRootValidator(b);
    }

    public List<EncircledArea> fetchFlatListFromBoard() {
        List<EncircledArea> areas = new ArrayList<>();
        EncircledAreaFetcher areaFecther = new EncircledAreaFetcher(b);
        Stack<Intersection> aTraiter = getEmptyBoardIntersectionsATraiter();
        while(!aTraiter.isEmpty()) {
            Intersection inter = aTraiter.pop();
            Optional<EncircledArea> optArea = areaFecther.fetchAreaFromIntersection(inter);
            if(optArea.isPresent()) {
                if(validator.isValid(optArea.get())) areas.add(optArea.get());
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
        EncircledAreaFetcher fetcher = new EncircledAreaFetcher(b);
        ArrayList<EncircledArea> topAeraGenerated = new ArrayList<>();
        for(EncircledArea area : flatlist) {
            topAeraGenerated.addAll(fetcher.fetchTopStickyEncirledFlatList(area));
        }
        flatlist.addAll(topAeraGenerated);
    }
}
