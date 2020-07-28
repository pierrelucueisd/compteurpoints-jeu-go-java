package PointCalculator.Fetcher;

import Board.Board;
import Board.Position;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Player.Color;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BorderFetcherTest {

    Board buildBoard(String representation) {
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent(), "attention erreure d'initialisetion du board");
        return optB.get();
    }


    List<Intersection> getAnneauContenu(int x, int y, Color colorBorder, Board b) {
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(x-1, y-1);
        List<Intersection> anneauContenu = encircledAreaFetcher.getAdjacencesTransitives(i, b,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != colorBorder
        );
        return anneauContenu;
    }

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    @Test
    void isBorderLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isLeftAdjacentOfGroup(new Position(2, 4));
        assertTrue(result);
    }

    @Test
    void isBorderLeftCasFauxRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isLeftAdjacentOfGroup(new Position(1, 4));
        assertFalse(result);
    }

    @Test
    void isBorderLeftCasFauxLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isLeftAdjacentOfGroup(new Position(3, 4));
        assertFalse(result);
    }

    @Test
    void isBorderLeftYPlusUn() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isLeftAdjacentOfGroup(new Position(2, 5));
        assertTrue(result);
    }

    @Test
    void isBorderLeftCasFauxBotomBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isLeftAdjacentOfGroup(new Position(3, 3));
        assertFalse(result);
    }

    @Test
    void isBorderRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-●-●-●-●-+-+-+\n" +
                        "+-+-●-+-+-●-+-+-+\n" +
                        "+-+-●-+-+-+-●-+-+\n" +
                        "+-+-+-●-●-●-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(6, 4));
        assertTrue(result);
    }

    @Test
    void isBorderRight2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-●-●-●-●-+-+-+\n" +
                        "+-+-●-+-+-●-+-+-+\n" +
                        "+-+-●-+-+-+-●-+-+\n" +
                        "+-+-+-●-●-●-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(5, 5));
        assertTrue(result);
    }

    @Test
    void isBorderRightFalseRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(7, 4));
        assertFalse(result);
    }

    @Test
    void isBorderRightFalseLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(5, 4));
        assertFalse(result);
    }

    @Test
    void isBorderRightFalseBottom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(5, 3));
        assertFalse(result);
    }

    @Test
    void isBorderRightFalseOtherBorderLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isRightAdjacentOfGroup(new Position(2, 4));
        assertFalse(result);
    }

    @Test
    void isBorderBottom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(3, 3));
        assertTrue(result);
    }

    @Test
    void isBorderBottom2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(4, 3));
        assertTrue(result);
    }

    @Test
    void isBorderBottomFalseBottom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(3, 2));
        assertFalse(result);
    }

    @Test
    void isBorderBottomFalseTop() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(3, 4));
        assertFalse(result);
    }

    @Test
    void isBorderBottomFalseTopBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(3, 6));
        assertFalse(result);
    }

    @Test
    void isBorderBottomFalseTopBorderLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isBottomAdjacentOfGroup(new Position(2, 4));
        assertFalse(result);
    }

    @Test
    void isBorderTop() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(3, 6));
        assertTrue(result);
    }

    @Test
    void isBorderTop2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(4, 6));
        assertTrue(result);
    }

    @Test
    void isBorderTop3() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(5, 5));
        assertTrue(result);
    }

    @Test
    void isBorderTopFalseTop() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(3, 7));
        assertFalse(result);
    }

    @Test
    void isBorderTopFalseBottom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(3, 5));
        assertFalse(result);
    }

    @Test
    void isBorderTopFalseBottomBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(3, 3));
        assertFalse(result);
    }

    @Test
    void isBorderTopFalseLeftBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isTopAdjacentOfGroup(new Position(2, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderBotom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-●-●-●-●-+-+-+\n" +
                        "+-+-●-+-+-●-+-+-+\n" +
                        "+-+-●-+-+-+-●-+-+\n" +
                        "+-+-+-●-●-●-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 3));
        assertTrue(result);
    }

    @Test
    void isInExternalBorderTop() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 6));
        assertTrue(result);
    }

    @Test
    void isInExternalBorderLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(2, 4));
        assertTrue(result);
    }

    @Test
    void isInExternalBorderRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(6, 4));
        assertTrue(result);
    }

    @Test
    void isInExternalBorderFalseInBotomLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseInBotomRigth() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(5, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseInTopLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 5));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseInTopRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-●-●-●-●-+-+-+\n" +
                        "+-+-●-+-+-●-+-+-+\n" +
                        "+-+-●-+-+-+-●-+-+\n" +
                        "+-+-+-●-●-●-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n" +
                        "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(4, 5));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseInMiddle() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(4, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutLeft() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(1, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutLeftBotomCoin() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(2, 3));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutRightBotomCoin() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(6, 3));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutBottom() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 2));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutRight() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(7, 4));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutTopRightCoin() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(6, 7));
        assertFalse(result);
    }

    @Test
    void isInExternalBorderFalseOutTop() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenuNoir = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenuNoir);
        boolean result = fetcher.isInExternalBorder(new Position(3, 7));
        assertFalse(result);
    }


    /** ============================================
     * Partie anneau contenu
     *==============================================
     */

    @Test
    void fetchExternalMinimalBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchExternalMinimalBorder();
        int i = 0;
        assertEquals(9, bordure.size());
    }

    @Test
    void fetchExternalMinimalBorder2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "●-+-+-+-●-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchExternalMinimalBorder();
        int i = 0;
        assertEquals(11, bordure.size());
    }

    @Test
    void fetchExternalMinimalBorder3() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-+-+-+\n" +
                "+-+-+-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "●-●-●-●-+-●-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "●-+-+-+-●-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchExternalMinimalBorder();
        int i = 0;
        assertEquals(15, bordure.size());
    }

    @Test
    void fetchFullBorder() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-+-+-+\n" +
                "+-+-+-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "●-●-●-●-+-●-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "●-+-+-+-●-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchFullBorder();
        int i = 0;
        assertEquals(17, bordure.size());
    }

    @Test
    void fetchFullBorder2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "●-●-●-●-+-●-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "●-+-+-+-●-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchFullBorder();
        int i = 0;
        assertEquals(18, bordure.size());
    }

    @Test
    void fetchFullBorder3() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "●-+-●-●-+-●-+-+-+\n" +
                "+-+-+-●-+-●-+-+-+\n" +
                "●-+-+-+-●-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        List<Intersection> anneauContenu = getAnneauContenu(4,5, Color.White, b);
        BorderFetcher fetcher = new BorderFetcher(b, anneauContenu);
        List<Intersection> bordure = fetcher.fetchFullBorder();
        int i = 0;
        assertEquals(16, bordure.size());
    }
}