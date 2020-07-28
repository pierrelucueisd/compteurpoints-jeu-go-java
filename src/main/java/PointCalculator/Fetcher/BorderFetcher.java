package PointCalculator.Fetcher;

import Board.Board;
import Board.Intersection;
import Board.Position;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BorderFetcher {

    Board board;
    List<Intersection> contenuAnneau;
    private static final Comparator<Intersection> comparatorY = Comparator.comparing( Intersection::getY);
    private static final Comparator<Intersection> comparatorX = Comparator.comparing( Intersection::getX);

    public BorderFetcher(Board b, List<Intersection> groupeContenu) {
        contenuAnneau = groupeContenu;
        board = b;
    }

    public List<Intersection> fetchExternalBorder() {
        return null;
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

}
