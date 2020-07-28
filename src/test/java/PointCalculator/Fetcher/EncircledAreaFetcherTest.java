package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import Player.Color;
import PointCalculator.EncircledArea;
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

    @Test
    void  fetchColorAreaFromIntersection_CasContenuMoinsNoir() {
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

        Intersection i = b.getIntersection(3, 4);
        EncircledArea area = encircledAreaFetcher.fetchColorAreaFromIntersection(i, Color.White);
        assertEquals(18, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(11, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchColorAreaFromIntersection_CasSousZone() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-●-+\n" +
                "●-+-●-●-●-●-+-●-+\n" +
                "●-+-●-+-○-●-+-●-+\n" +
                "●-+-●-●-●-●-○-●-+\n" +
                "●-+-+-+-○-○-○-●-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(2, 2);
        EncircledArea area = encircledAreaFetcher.fetchColorAreaFromIntersection(i, Color.White);
        assertEquals(26, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(30, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(18, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchColorAreaFromIntersection_CasSousZone2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-●-+\n" +
                "●-+-●-●-●-●-+-●-+\n" +
                "●-+-●-+-○-●-+-●-+\n" +
                "●-+-●-●-●-●-○-●-+\n" +
                "●-+-+-+-○-○-○-●-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 4);
        EncircledArea area = encircledAreaFetcher.fetchColorAreaFromIntersection(i, Color.White);
        assertEquals(10, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(2, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(2, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchAreaFromIntersection_CasContenuMoinsNoir() {
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

        Intersection i = b.getIntersection(3, 4);
        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertEquals(18, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(11, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchAreaFromIntersection_CasSousZone() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-●-+\n" +
                "●-+-●-●-●-●-+-●-+\n" +
                "●-+-●-+-○-●-+-●-+\n" +
                "●-+-●-●-●-●-○-●-+\n" +
                "●-+-+-+-○-○-○-●-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(2, 2);
        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertEquals(26, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(30, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(18, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchAreaFromIntersection_CasSousZone2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "●-+-+-+-+-+-+-●-+\n" +
                "●-+-●-●-●-●-+-●-+\n" +
                "●-+-●-+-○-●-+-●-+\n" +
                "●-+-●-●-●-●-○-●-+\n" +
                "●-+-+-+-○-○-○-●-+\n" +
                "●-●-●-●-●-●-●-●-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 4);
        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertEquals(10, area.getFullBorder().size(), "Bordure non conforme");
        assertEquals(2, area.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(2, area.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchFirstAscendantStickyEncercling_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-○-●-+-+-●-○-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area);
        assertTrue(optTopArea.isPresent(), "Absence de détection de zone parente");
        EncircledArea topArea = optTopArea.get();
        assertEquals(14, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchFirstAscendantStickyEncercling2_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-○-○-○-○-+-+-+\n" +
                        "+-○-●-●-●-●-○-+-+\n" +
                        "+-○-●-+-+-●-○-○-+\n" +
                        "+-○-●-●-●-●-○-+-+\n" +
                        "+-+-○-○-○-○-+-+-+\n" +
                        "+-+-+-+-○-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n" +
                        "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area);
        assertTrue(optTopArea.isPresent(), "Absence de détection de zone parente");
        EncircledArea topArea = optTopArea.get();
        assertEquals(16, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchFirstAscendantStickyEncercling3_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-○-●-+-+-●-○-○-+\n" +
                "+-○-●-●-+-●-○-+-+\n" +
                "+-+-○-○-●-○-+-+-+\n" +
                "+-+-+-+-○-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area);
        assertTrue(optTopArea.isPresent(), "Absence de détection de zone parente");
        EncircledArea topArea = optTopArea.get();
        assertEquals(15, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(13, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }




    @Test
    void  fetchTopStickyEncirler_CasBasique() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-○-●-+-+-●-○-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        EncircledArea area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        EncircledArea topArea = encircledAreaFetcher.fetchTopStickyEncirler(area);
        assertEquals(14, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }


}