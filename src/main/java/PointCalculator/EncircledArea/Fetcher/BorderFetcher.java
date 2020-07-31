package PointCalculator.EncircledArea.Fetcher;

import Board.Board;
import Board.Intersection;
import Board.Position;
import Player.Color;

import java.util.*;

public class BorderFetcher {

    private Board board;
    private List<Intersection> contenuAnneau;
    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);
    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    public BorderFetcher(Board b, List<Intersection> groupeContenu) {
        contenuAnneau = groupeContenu;
        board = b;
    }

    public List<Intersection> fetchFullBorder() {
        List<Intersection> bordureComplete = new ArrayList<Intersection>();
        List<Intersection> bordureMinimale = fetchExternalMinimalBorder();
        if(bordureMinimale.isEmpty()) return bordureComplete;
        if(!bordureMinimale.get(0).getOccupation().isPresent()) return bordureComplete;
        Color borderColor = bordureMinimale.get(0).getOccupation().get();
        Stack<Intersection> aTraiter = new Stack<Intersection>();
        aTraiter.addAll(bordureMinimale);
        while(!aTraiter.isEmpty()){
            Intersection intersectiontraiter = aTraiter.pop();
            List<Intersection> tempBorderElemFullAdjacency = EncircledAreaFetcher.getAdjacencesTransitives(
                    intersectiontraiter, board, intersection -> {
                        if(!intersection.getOccupation().isPresent()) return false;
                        Color colorIntersection = intersection.getOccupation().get();
                        return colorIntersection == borderColor
                                && !bordureComplete.contains(intersection);
                    }
            );
            bordureComplete.addAll(tempBorderElemFullAdjacency);
        }
        return bordureComplete;
    }

    public List<Intersection> fetchExternalMinimalBorder() {
        List<Intersection> bordure = new ArrayList<Intersection>();
        if(contenuAnneau.isEmpty()) return bordure;
        bordure = EncircledAreaFetcher.getAdjacencesTransitives(contenuAnneau.get(0), board, intersection -> {
            if(contenuAnneau.contains(intersection)) return true;
            Position p = new Position(intersection.getX(), intersection.getY());
            if(isInExternalBorder(p)) return true;
            return false;
        });
        bordure.removeAll(contenuAnneau);
        return bordure;
    }

    protected boolean isLeftAdjacentOfGroup(Position p) {
        Intersection i = board.getIntersection(p);
        Optional<Intersection> optDroite = contenuAnneau.stream().filter(
                intersection -> intersection.getY() == i.getY()
        ).min(comparatorX);
        if(!optDroite.isPresent()) return false;
        Intersection interDroite = optDroite.get();
        if(i.getX()+1 == interDroite.getX()) return true;
        return false;
    }

    protected boolean isRightAdjacentOfGroup(Position p) {
        Intersection i = board.getIntersection(p);
        Optional<Intersection> optDroite = contenuAnneau.stream().filter(
                intersection -> intersection.getY() == i.getY()
        ).max(comparatorX);
        if(!optDroite.isPresent()) return false;
        Intersection interDroite = optDroite.get();
        if(i.getX()-1 == interDroite.getX()) return true;
        return false;
    }

    protected boolean isBottomAdjacentOfGroup(Position p) {
        Intersection i = board.getIntersection(p);
        Optional<Intersection> optDroite = contenuAnneau.stream().filter(
                intersection -> intersection.getX() == i.getX()
        ).min(comparatorY);
        if(!optDroite.isPresent()) return false;
        Intersection interDroite = optDroite.get();
        if(i.getY()+1 == interDroite.getY()) return true;
        return false;
    }

    protected boolean isTopAdjacentOfGroup(Position p) {
        Intersection i = board.getIntersection(p);
        Optional<Intersection> optDroite = contenuAnneau.stream().filter(
                intersection -> intersection.getX() == i.getX()
        ).max(comparatorY);
        if(!optDroite.isPresent()) return false;
        Intersection interDroite = optDroite.get();
        if(i.getY()-1 == interDroite.getY()) return true;
        return false;
    }

    protected boolean isInExternalBorder(Position p) {
        return isLeftAdjacentOfGroup(p) || isRightAdjacentOfGroup(p)
                || isTopAdjacentOfGroup(p) || isBottomAdjacentOfGroup(p);
    }

}
