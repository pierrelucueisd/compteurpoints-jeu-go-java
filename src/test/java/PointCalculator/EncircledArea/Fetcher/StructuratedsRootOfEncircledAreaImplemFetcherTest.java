package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.EncircledArea.EncircledAreaValidator.RootValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StructuratedsRootOfEncircledAreaImplemFetcherTest {

    final static int taille = 9;

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
    void fetchRotts() {
    }

    @Test
    void structurateElementsOfList_CasBasique() {
        String representation =
                "+-+-○-+-+-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-●-●-●-●-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-○-+-○-●-+-●-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-●-●-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, 15);
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcher(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(3, rootElements.size());
    }

    @Test
    void structurateElementsOfList_CasBasique2() {
        String representation =
                "+-+-○-+-+-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-●-●-●-●-●-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-○-+-○-●-+-●-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-●-●-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, 15);
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcher(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(3, rootElements.size());
    }

    @Test
    void structurateElementsOfList_CasBasique3() {
        String representation =
                "+-+-○-+-+-○-●-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-●-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-●-●-●-●-●-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-○-+-○-●-+-●-+-●-+-+-+-+\n" +
                "+-+-●-○-○-○-●-●-●-+-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-●-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-●-●-●-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, 15);
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcher(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(2, rootElements.size());
    }

    @Test
    void structurateElementsOfList_CasBasique4() {
        String representation =
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-○-○-○-○-○-+-+-+-+-+\n" +
                "+-+-+-+-+-○-●-●-●-○-+-+-+-+-+\n" +
                "+-+-+-+-+-○-●-+-●-○-+-+-+-+-+\n" +
                "+-+-+-+-+-○-●-●-●-○-+-+-+-+-+\n" +
                "+-+-+-+-+-○-○-○-○-○-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, 15);
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcher(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(1, rootElements.size());
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
        StructuredEncircledAreaFetcher structureFecther = new StructuredEncircledAreaFetcher(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(2, rootElements.size());
    }
}