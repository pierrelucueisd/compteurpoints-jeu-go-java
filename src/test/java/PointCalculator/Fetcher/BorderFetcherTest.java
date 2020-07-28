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
        List<Intersection> anneauContenu = encircledAreaFetcher.getAdjacencesTransitives(i,
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
}