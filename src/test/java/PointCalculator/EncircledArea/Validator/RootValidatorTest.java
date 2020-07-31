package PointCalculator.EncircledArea.Validator;

import Board.Board;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Board.Intersection;
import PointCalculator.EncircledArea.EncircledArea;
import PointCalculator.EncircledArea.Fetcher.EncircledAreaFetcher;
import PointCalculator.EncircledArea.Fetcher.EncircledAreaFetcherImplem;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RootValidatorTest {

    Board buildBoard(String representation, int size) {
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation, size);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent(), "attention erreure d'initialisetion du board");
        return optB.get();
    }

    EncircledAreaFetcher getFetcherFromBoard(Board b) {
        return new EncircledAreaFetcherImplem(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
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
        EncircledAreaFetcher encircledAreaFetcher = getFetcherFromBoard(b);
        Optional<EncircledArea> optArea = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(optArea.isPresent(), "Attention précondition zone existant fausse");
        RootValidator validator = new RootValidator(b);
        assertTrue(
                validator.isToBigAndContentTouchingBoardBorder(optArea.get())
        );
    }
}