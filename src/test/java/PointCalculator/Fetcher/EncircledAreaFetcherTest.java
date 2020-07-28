package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Player.Color;
import PointCalculator.Fetcher.EncircledAreaFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncircledAreaFetcherTest {

    Board buildBoard(String representation) {
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent(), "attention erreure d'initialisetion du board");
        return optB.get();
    }

    /* LÉGENDE
     *  1. Blanc:   ●
     *  2. Noir:    ○
     */

    @Test
    void fetch() {
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
        System.out.println(b);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        encircledAreaFetcher.fetch();
        int y = 9;
    }

    @Test
    void getAdjacencesTransitivesCasDeBase() {
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
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(3, 4);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(5, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasContenuSimple() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-●-●-●-●-●-●-+\n" +
                        "+-+-●-+-+-+-+-●-+\n" +
                        "+-+-●-+-+-+-+-●-+\n" +
                        "+-+-●-+-○-+-+-●-+\n" +
                        "●-+-●-●-●-●-●-●-+\n" +
                        "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(3, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(12, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasContenu() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-+\n" +
                "+-+-●-+-○-○-○-●-+\n" +
                "+-+-●-+-○-+-○-●-+\n" +
                "+-+-●-+-○-○-○-●-+\n" +
                "●-+-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(3, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(12, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasContenuMoinsNoir() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-●-●-+\n" +
                "+-+-●-+-○-○-○-●-+\n" +
                "+-+-●-+-○-●-○-●-+\n" +
                "+-+-●-+-○-○-○-●-+\n" +
                "●-+-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(3, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(11, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasContenuMoinsNoir2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-●-●-●-+-●-+\n" +
                "+-●-+-●-+-●-+-●-+\n" +
                "+-●-+-●-●-●-○-●-+\n" +
                "+-●-+-○-○-○-○-●-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(2, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(16, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasContenuMoinsNoir3() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "+-●-+-+-+-+-+-●-+\n" +
                "+-●-+-●-●-●-+-●-+\n" +
                "+-●-+-●-+-●-+-●-+\n" +
                "+-●-+-●-●-●-○-●-+\n" +
                "+-●-○-○-○-○-○-●-+\n" +
                "+-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(2, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.White
        );
        assertEquals(16, anneauContenuNoir.size());
    }

    @Test
    void getAdjacencesTransitivesCasBordureNoire() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-○-○-○-○-○-○-○-+\n" +
                "+-○-+-+-+-+-+-○-+\n" +
                "+-○-+-○-○-○-+-○-+\n" +
                "+-○-+-○-+-○-+-○-+\n" +
                "+-○-+-○-○-○-+-○-+\n" +
                "+-○-+-+-●-●-●-○-+\n" +
                "+-○-○-○-○-○-○-○-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        Intersection i = b.getIntersection(2, 2);
        List<Intersection> anneauContenuNoir = encircledAreaFetcher.getAdjacencesTransitives(i,
                inter -> !inter.getOccupation().isPresent() || inter.getOccupation().get() != Color.Black
        );
        assertEquals(16, anneauContenuNoir.size());
    }


}