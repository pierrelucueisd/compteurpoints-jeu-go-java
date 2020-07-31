package PointCalculator.visitor;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Fetcher.StructuredEncircledAreaFetcher;
import PointCalculator.EncircledArea.Fetcher.StructuredEncircledAreaFetcherImplem;
import PointCalculator.EncircledArea.Validator.RootValidator;
import PointCalculator.EncircledArea.Validator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorVisitorTest {

    Board buildBoard(String representation, int size) {
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation, size);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent(), "attention erreure d'initialisetion du board");
        return optB.get();
    }

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    @Test
    void visit_CasBasique() {
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
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcherImplem(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
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