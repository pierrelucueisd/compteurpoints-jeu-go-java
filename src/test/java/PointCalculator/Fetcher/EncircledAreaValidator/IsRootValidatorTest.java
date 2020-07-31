package PointCalculator.Fetcher.EncircledAreaValidator;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Board.Intersection;
import PointCalculator.EncircledArea;
import PointCalculator.Fetcher.EncircledAreaFetcher;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IsRootValidatorTest {

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
    void isAdjacentOfAllBoardSides_CasBasique() {
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
        Intersection i = b.getIntersection(0, 0);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertTrue(
                validator.isAdjacentOfAllBoardSides(optArea.get())
        );
    }

    @Test
    void isContentToBigAndTouchBoardBorder() {
        String representation =
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n";

        Board b = buildBoard(representation, 9);
        Intersection i = b.getIntersection(0, 0);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertTrue(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }

    @Test
    void isContentToBigAndTouchBoardBorder_cas2() {
        String representation =
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-●-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n" +
                "+-+-+-+-●-+-+-+-+\n";

        Board b = buildBoard(representation, 9);
        Intersection i = b.getIntersection(5, 0);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertFalse(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }

    @Test
    void isContentToBigAndTouchBoardBorder_cas3() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n";

        Board b = buildBoard(representation, 9);
        Intersection i = b.getIntersection(5, 5);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertFalse(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }

    @Test
    void isContentToBigAndTouchBoardBorder_cas4() {
        String representation =
                "+-●-●-●-●-●-●-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";

        Board b = buildBoard(representation, 9);
        Intersection i = b.getIntersection(5, 5);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertFalse(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }

    @Test
    void isContentToBigAndTouchBoardBorder_cas5() {
        String representation =
                "+-●-●-●-+-●-●-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";

        Board b = buildBoard(representation, 9);
        Intersection i = b.getIntersection(5, 5);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        IsRootValidator validator = new IsRootValidator(b);
        assertTrue(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }
}