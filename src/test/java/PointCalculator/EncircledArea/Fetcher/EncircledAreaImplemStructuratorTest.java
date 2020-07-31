package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderForTests;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Validator.EncircledAreaValidator;
import PointCalculator.EncircledArea.Validator.RootValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncircledAreaImplemStructuratorTest {

    final static int taille = 9;

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
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
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
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
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
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
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
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
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
        List<EncircledArea> areas = fetchFlatListFromBoard(b);
        EncircledAreaStructurator structurator = generateStructurator(b);
        List<EncircledArea> rootElements = structurator.structurateElementsOfList(areas);
        assertEquals(2, rootElements.size());
    }





}