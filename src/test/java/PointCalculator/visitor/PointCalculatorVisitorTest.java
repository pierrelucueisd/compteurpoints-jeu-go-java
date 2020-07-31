package PointCalculator.visitor;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea;
import PointCalculator.Fetcher.EncircledAreaFlatListFetcher;
import PointCalculator.Fetcher.EncircledAreaStructurator;
import PointCalculator.EncircledAreaValidator.RootValidator;
import PointCalculator.EncircledAreaValidator.EncircledAreaValidator;
import PointCalculator.EncircledAreaValidator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorVisitorTest {

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
        EncircledAreaValidator areaNotToBigValidator = new RootValidator(b);
        return new EncircledAreaStructurator(b, areaNotToBigValidator);
    }

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    @Test
    void visit() {
    }

    @Test
    void structurateElementsOfList_CasBasique5() {
        String representation =
                "+-○-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-○-+-+-+\n" +
                "+-○-●-+-●-○-+-+-+\n" +
                "+-○-●-●-●-○-+-+-+\n" +
                "+-○-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-●-+\n" +
                "+-●-●-●-●-●-+-+-+\n" +
                "+-●-○-○-○-●-+-+-+\n" +
                "+-●-○-+-○-●-+-+-+\n";
        Board b = buildBoard(representation, 9);
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
        EncircledAreaVisitor pointCalculatorVisitor = new PointCalculatorVisitor(
                new TakableValidatorNaive(), new PlayersScoreStats()
        );
        for(EncircledArea area: rootElements) {
            pointCalculatorVisitor.visit(area);
        }
        int blackPoints = pointCalculatorVisitor.getBlackPlayerPoints();
        int whitePoints = pointCalculatorVisitor.getWhitePlayerPoints();
        assertEquals(22, blackPoints, "Problème de décompte de points de noir");
        assertEquals(18, whitePoints, "Problème de décompte de points avec Blanc");
    }

}