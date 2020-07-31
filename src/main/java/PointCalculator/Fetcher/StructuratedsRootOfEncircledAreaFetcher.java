package PointCalculator.Fetcher;

import Board.Board;
import PointCalculator.EncircledArea;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.RootValidator;

import java.util.List;

public class StructuratedsRootOfEncircledAreaFetcher {

    private Board b;

    public StructuratedsRootOfEncircledAreaFetcher(Board b) {
        this.b = b;
    }

    public List<EncircledArea> fetchRotts() {
        EncircledAreaFlatListFetcher flarListFecther = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = flarListFecther.fetchFlatListFromBoard();
        EncircledAreaValidator areaNotToBigValidator = new RootValidator(b);
        EncircledAreaStructurator structurator = new EncircledAreaStructurator(b, areaNotToBigValidator);
        return structurator.structurateElementsOfList(areas);
    }
}
