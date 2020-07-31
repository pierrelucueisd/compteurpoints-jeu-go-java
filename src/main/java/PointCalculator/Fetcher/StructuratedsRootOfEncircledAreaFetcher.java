package PointCalculator.Fetcher;

import Board.Board;
import PointCalculator.EncircledArea;
import PointCalculator.EncircledAreaImplem;
import PointCalculator.EncircledAreaValidator.EncircledAreaValidator;

import java.util.List;

public class StructuratedsRootOfEncircledAreaFetcher {

    private Board b;
    EncircledAreaValidator rootValidator;

    public StructuratedsRootOfEncircledAreaFetcher(Board b, EncircledAreaValidator rootValidator) {
        this.b = b;
        this.rootValidator = rootValidator;
    }

    public List<EncircledArea> fetch() {
        EncircledAreaFlatListFetcher flarListFecther = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = flarListFecther.fetchFlatListFromBoard();
        EncircledAreaStructurator structurator = new EncircledAreaStructurator(b, rootValidator);
        return structurator.structurateElementsOfList(areas);
    }
}
