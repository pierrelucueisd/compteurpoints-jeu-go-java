package PointCalculator.EncirledAreaInterfaceComparator;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Player.Color;
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
        EncircledArea areaB = encircledAreaFetcher.fetchAreaFromIntersection(i);

        i = b.getIntersection(4, 5);
        EncircledArea areaA = encircledAreaFetcher.fetchAreaFromIntersection(i);


        assertTrue(EncirleAreaComparator.isMinleftOfAreaAisafterBMinLeft(areaA, areaB));
        /*assertEquals(18, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(11, area.getRingContent().size(), "Contenu aneau non conforme");*/
    }
}