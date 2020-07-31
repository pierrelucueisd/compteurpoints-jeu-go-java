package PointCalculator.Fetcher;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StructuratedsRootOfEncircledAreaFetcherTest {

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
        StructuratedsRootOfEncircledAreaFetcher structureFecther = new StructuratedsRootOfEncircledAreaFetcher(b);
        List<EncircledArea> rootElements = structureFecther.fetchRotts();
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
        StructuratedsRootOfEncircledAreaFetcher structureFecther = new StructuratedsRootOfEncircledAreaFetcher(b);
        List<EncircledArea> rootElements = structureFecther.fetchRotts();
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
        StructuratedsRootOfEncircledAreaFetcher structureFecther = new StructuratedsRootOfEncircledAreaFetcher(b);
        List<EncircledArea> rootElements = structureFecther.fetchRotts();
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
        StructuratedsRootOfEncircledAreaFetcher structureFecther = new StructuratedsRootOfEncircledAreaFetcher(b);
        List<EncircledArea> rootElements = structureFecther.fetchRotts();
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
        StructuratedsRootOfEncircledAreaFetcher structureFecther = new StructuratedsRootOfEncircledAreaFetcher(b);
        List<EncircledArea> rootElements = structureFecther.fetchRotts();
        assertEquals(2, rootElements.size());
    }
}