package PointCalculator.Fetcher;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncircledAreaFlatListFetcherTest {

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
    void fetchFlatListNoStickyFromBoard_CasBasiqueZoneCount() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-○-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(2, areas.size());
    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasBasique2ZoneCount() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-○-○-○-+-+-+\n" +
                "●-+-+-○-+-○-+-+-+\n" +
                "●-+-+-○-○-○-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(3, areas.size());
    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasBasique2ZoneCount4() {
        String representation =
                "+-+-○-+-+-○-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-○-○-○-+-+-+\n" +
                "●-+-+-○-+-○-+-+-+\n" +
                "●-+-+-○-○-○-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(4, areas.size());
    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasBasique2ZoneCount5() {
        String representation =
                "+-+-○-+-+-○-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-●-●\n" +
                "+-+-+-○-○-○-●-+-+\n" +
                "●-+-+-○-+-○-●-+-+\n" +
                "●-+-+-○-○-○-●-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(6, areas.size());
    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasConcentrique() {
        String representation =
                "+-+-●-●-●-●-+-+-+\n" +
                "+-●-○-○-○-○-●-+-+\n" +
                "●-○-●-●-●-●-○-●-+\n" +
                "●-○-●-+-+-●-○-○-●\n" +
                "●-○-●-●-●-●-○-●-+\n" +
                "+-●-○-○-○-○-●-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(4, areas.size());
    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasConcentriqueFreres() {
        String representation =
                "+-○-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-○-+-+-+\n" +
                "+-○-●-+-●-○-+-+-+\n" +
                "+-○-●-●-●-○-+-+-+\n" +
                "+-○-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-+-+-+\n" +
                "+-●-○-○-○-●-+-+-+\n" +
                "+-●-○-+-○-●-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(3, areas.size());

    }

    @Test
    void fetchFlatListNoStickyFromBoard_Cas2LinesBrother() {
        String representation =
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n" +
                "+-+-+-+-○-●-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(2, areas.size());

    }

    @Test
    void fetchFlatListNoStickyFromBoard_CasNoMansLand() {
        String representation =
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFlatListFetcher fetcher = new EncircledAreaFlatListFetcher(b);
        List<EncircledArea> areas = fetcher.fetchFlatListNoStickyFromBoard();
        assertEquals(2, areas.size());

    }

}