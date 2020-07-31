package PointCalculator.EncircledArea.Comparator;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilderForTests;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Fetcher.EncircledAreaFetcher;
import PointCalculator.EncircledArea.Fetcher.EncircledAreaFetcherImplem;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncirleAreaComparatorTest {

    private final static int taille = 9;

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    private EncircledAreaFetcher getFetchetFromBoard(Board b) {
        return new EncircledAreaFetcherImplem(b);
    }

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

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
        Board b = BoardBuilderForTests.buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = getFetchetFromBoard(b);

        Intersection i = b.getIntersection(0, 5);
        i = b.getIntersection(2, 3);
        Optional<EncircledArea> areaB = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(areaB.isPresent(), "ereure areaB non présente.");

    }
}