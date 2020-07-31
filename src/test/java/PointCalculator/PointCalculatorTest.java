package PointCalculator;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.Fetcher.EncircledAreaFlatListFetcher;
import PointCalculator.Fetcher.EncircledAreaStructurator;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.Fetcher.EncircledAreaValidator.EncircledAreaValidatorInterface;
import PointCalculator.visitor.EncircledAreaVisitor;
import PointCalculator.visitor.PointCalculatorVisitor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorTest {

    final static int taille = 9;

    Board buildBoard(String representation, int size) {
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation, size);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent(), "attention erreure d'initialisetion du board");
        return optB.get();
    }

    private List<EncircledArea> fetchFlatListFromBoard(Board b) {
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        return fetcher.fetchFlatListFromBoard();
    }

    private EncircledAreaStructurator generateStructurator(Board b) {
        EncircledAreaValidatorInterface areaNotToBigValidator = new EncircledAreaValidator(b);
        return new EncircledAreaStructurator(b, areaNotToBigValidator);
    }

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    @Test
    void publicCalculateFromList() {
    }

    @Test
    void structurateElementsOfList_CasBasique5() {
        String representation =
                "+-○-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-○-+-●-+\n" +
                "+-○-●-+-●-○-+-+-+\n" +
                "+-○-●-●-●-○-+-●-+\n" +
                "+-○-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-●-+\n" +
                "+-●-●-●-●-●-+-+-+\n" +
                "+-●-○-○-○-●-+-○-+\n" +
                "+-●-○-+-○-●-+-+-+\n";
        Board b = buildBoard(representation, 9);
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
        EncircledAreaVisitor pointCalculatorVisitor = new PointCalculatorVisitor();
        PointCalculator pointCalculator = new PointCalculator(b, rootElements, pointCalculatorVisitor);
        pointCalculator.calculate();
        int whitePoints = pointCalculator.getWhitePoints();
        int blaclPoints = pointCalculator.getBlackPoints();

        assertEquals(23, blaclPoints, "Problème de décompte de points de noir");
        assertEquals(21, whitePoints, "Problème de décompte de points avec Blanc");
    }
}