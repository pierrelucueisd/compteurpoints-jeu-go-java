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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(18, area.get().getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, area.get().getFullContent().size(), "Contenu complet non conforme");
        assertEquals(3, area.get().getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchAreaFromIntersection_CasContenuMoinsNoir2() {
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(18, area.get().getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, area.get().getFullContent().size(), "Contenu complet non conforme");
        assertEquals(3, area.get().getRingContent().size(), "Contenu aneau non conforme");
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(2, 2);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(26, area.get().getFullBorder().size(), "Bordure non conforme");
        assertEquals(30, area.get().getFullContent().size(), "Contenu complet non conforme");
        assertEquals(14, area.get().getRingContent().size(), "Contenu aneau non conforme");
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 4);
        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        assertEquals(10, area.get().getFullBorder().size(), "Bordure non conforme");
        assertEquals(2, area.get().getFullContent().size(), "Contenu complet non conforme");
        assertEquals(1, area.get().getRingContent().size(), "Contenu aneau non conforme");
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area.get(), true);
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area.get(), true);
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        Optional<EncircledArea> optTopArea = encircledAreaFetcher.fetchFirstAscendantStickyEncercling(area.get(), true);
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
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        EncircledArea topArea = encircledAreaFetcher.fetchTopStickyEncirler(area.get());
        assertEquals(14, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchTopStickyEncirler_CasBasique2() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-○-●-+-+-●-○-○-+\n" +
                "+-○-●-●-●-●-○-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        EncircledArea topArea = encircledAreaFetcher.fetchTopStickyEncirler(area.get());
        assertEquals(15, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(12, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }

    @Test
    void  fetchTopStickyEncirler_CasBasique3() {
        String representation =
                "+-+-●-●-●-●-+-+-+\n" +
                "+-●-○-○-○-○-●-+-+\n" +
                "●-○-●-●-●-●-○-●-+\n" +
                "●-○-●-+-+-●-○-○-●\n" +
                "●-○-●-●-●-●-○-●-+\n" +
                "+-●-○-○-○-○-●-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 5);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        EncircledArea topArea = encircledAreaFetcher.fetchTopStickyEncirler(area.get());
        assertEquals(18, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(27, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(0, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }



    @Test
    void  fetchTopStickyEncirler_CasNonEncerclé() {
        String representation =
                "+-+-○-+-+-○-+-+-+\n" +
                "+-+-○-○-○-○-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-●-●\n" +
                "+-+-+-○-○-○-●-+-+\n" +
                "●-+-+-○-+-○-●-+-+\n" +
                "●-+-+-○-○-○-●-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(3, 8);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(area.isPresent(), "ereure Areab non présente.");
        EncircledArea topArea = encircledAreaFetcher.fetchTopStickyEncirler(area.get());
        assertEquals(6, topArea.getFullBorder().size(), "Bordure non conforme");
        assertEquals(2, topArea.getFullContent().size(), "Contenu complet non conforme");
        assertEquals(2, topArea.getRingContent().size(), "Contenu aneau non conforme");
    }


    @Test
    void  fetchTopStickyEncirler_CasNoMansLand() {
        String representation =
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n" +
                "+-+-+-○-+-●-+-+-+\n";
        Board b = buildBoard(representation, taille);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);

        Intersection i = b.getIntersection(4, 0);

        Optional<EncircledArea> area = encircledAreaFetcher.fetchAreaFromIntersection(i);
        assertTrue(!area.isPresent(), "ereure no mans land ignoré");
    }











}