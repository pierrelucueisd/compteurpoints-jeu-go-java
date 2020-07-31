package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Validator.EncircledAreaValidator;

import java.util.List;

public class StructuredEncircledAreaFetcherImplem implements StructuredEncircledAreaFetcher {

    private Board b;
    private EncircledAreaValidator rootValidator;

    public StructuredEncircledAreaFetcherImplem(Board b, EncircledAreaValidator rootValidator) {
        this.b = b;
        this.rootValidator = rootValidator;
    }

    //retourne les racines
    public List<EncircledArea> fetch() {
        EncircledAreaFlatListFetcher flarListFecther = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = flarListFecther.fetchFlatListFromBoard();
        EncircledAreaStructurator structurator = new EncircledAreaStructurator(b, rootValidator);
        return structurator.structurateElementsOfList(areas);
    }
}
