package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Builder.BoardBuilderForTests;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Validator.RootValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StructuratedsRootOfEncircledAreaImplemFetcherTest {

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
        Board b = BoardBuilderForTests.buildBoard(representation, 15);
        StructuredEncircledAreaFetcherImplem structureFecther = new StructuredEncircledAreaFetcherImplem(
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
        Board b = BoardBuilderForTests.buildBoard(representation, 15);
        StructuredEncircledAreaFetcherImplem structureFecther = new StructuredEncircledAreaFetcherImplem(
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
        Board b = BoardBuilderForTests.buildBoard(representation, 15);
        StructuredEncircledAreaFetcherImplem structureFecther = new StructuredEncircledAreaFetcherImplem(
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
        Board b = BoardBuilderForTests.buildBoard(representation, 15);
        StructuredEncircledAreaFetcherImplem structureFecther = new StructuredEncircledAreaFetcherImplem(
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
        Board b = BoardBuilderForTests.buildBoard(representation, 9);
        StructuredEncircledAreaFetcherImplem structureFecther = new StructuredEncircledAreaFetcherImplem(
                b, new RootValidator(b)
        );
        List<EncircledArea> rootElements = structureFecther.fetch();
        assertEquals(2, rootElements.size());
    }
}