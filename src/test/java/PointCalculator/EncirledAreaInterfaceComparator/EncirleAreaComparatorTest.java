package PointCalculator.EncirledAreaInterfaceComparator;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import PointCalculator.EncircledArea;
import PointCalculator.Fetcher.EncircledAreaFetcher;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncirleAreaComparatorTest {

    private final static int taille = 9;

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
    void isAreaAisInAreaB() {
    }

    @Test
    void isMinleftOfAreaAisafterBMinLeft() {
    }

    @Test
    void isMinleftOfAreaAisafterBMinLeft_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-●-+-+\n" +
                "+-●-+-○-○-○-●-+-+\n" +
                "+-●-+-○-+-○-●-+-+\n" +
                "+-●-+-○-○-○-●-+-+\n" +
                "+-●-●-●-●-●-●-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(2, 4);
        Optional<EncircledArea> areaB = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaB.isPresent(), "ereure Areab non présente.");

        i = b.getIntersection(4, 5);
        Optional<EncircledArea> areaA = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaA.isPresent(), "ereure Areab non présente.");

        assertTrue(EncirleAreaComparator.isMinleftOfAreaAisafterBMinLeft(areaA.get(), areaB.get()));
    }

    @Test
    void getMaxX_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(6, EncirleAreaComparator.getMaxX(area.get().getFullContent(), b));
    }

    @Test
    void getMinX_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(2, EncirleAreaComparator.getMinX(area.get().getFullContent(), b));
    }

    @Test
    void getMinY_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(2, EncirleAreaComparator.getMinY(area.get().getFullContent(), b));
    }

    @Test
    void getMaxY_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-+-●-●-+-●-●-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(6, EncirleAreaComparator.getMaxY(area.get().getFullContent(), b));
    }

    @Test
    void testIsAreaAisInAreaB_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-●-+-+\n" +
                "+-●-+-○-○-○-●-+-+\n" +
                "+-●-+-○-+-○-●-+-+\n" +
                "+-●-+-○-○-○-●-+-+\n" +
                "+-●-●-●-●-●-●-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 5);
        Optional<EncircledArea> areaA = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaA.isPresent(), "ereure Areab non présente.");

        i = b.getIntersection(2, 4);
        Optional<EncircledArea> areaB = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaB.isPresent(), "ereure Areab non présente.");

        assertTrue(EncirleAreaComparator.isAreaAisInAreaB(areaA.get(), areaB.get(), b));
    }

    @Test
    void testIsAreaAisInAreaB_CasBasique2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-+-+-+-+-+\n" +
                "○-○-○-●-+-+-+-+-+\n" +
                "+-+-○-●-+-+-+-+-+\n" +
                "○-○-○-●-+-+-+-+-+\n" +
                "●-●-+-●-+-+-+-+-+\n" +
                "+-+-●-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(0, 5);
        //Optional<EncircledArea> areaA = encircledAreaFetcher.fetchAreaFromIntersection(i);
        //assertTrue(areaA.isPresent(), "ereure areaA non présente.");

        i = b.getIntersection(2, 3);
        Optional<EncircledArea> areaB = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaB.isPresent(), "ereure areaB non présente.");

        //assertTrue(EncirleAreaComparator.isAreaAisInAreaB(areaA.get(), areaB.get(), b));
    }
}