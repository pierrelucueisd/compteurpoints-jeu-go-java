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
    void getAnneauInterieurCasDeBase() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(5, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasContenuSimple() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(12, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasContenu() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(12, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasContenuMoinsNoir() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(11, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasContenuMoinsNoir2() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(16, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasContenuMoinsNoir3() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.White);
        assertEquals(16, anneauContenuNoir.size());
    }

    @Test
    void getAnneauInterieur_CasBordureNoire() {
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
        List<Intersection> anneauContenuNoir  = encircledAreaFetcher.getAnneauInterieur(i, Color.Black);
        assertEquals(16, anneauContenuNoir.size());
    }

    @Test
    void fetchColorAreaFromIntersection_CasDeBase() {
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
        EncircledArea area = encircledAreaFetcher.fetchColorAreaFromIntersection(i, Color.White);
        assertEquals(11, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(5, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(5, area.getRingContent().size(), "Contenu aneau non conforme");
    }


}