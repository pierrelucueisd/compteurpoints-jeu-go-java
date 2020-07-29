package Board.Builder;

import Board.Board;
import Board.Intersection;
import Board.Position;
import Player.Color;

import java.util.Optional;

//construit un board sans enlever les zones capturées
public class BoardBuilderFromBoardRepresentation implements BoardBuilder {
    private String str;
    private int size;
    public BoardBuilderFromBoardRepresentation(String _str, int size) {
        this.str = _str;
        this.size = size;
    }

    @Override
    public Optional<Board> build() {
        Board b = new Board(size);
        String[] lignes = str.split("\n");
        if(lignes.length != b.getSize()) return Optional.empty();
        int noLigneMat = 0;
        for(int i = lignes.length-1; i>=0; i--) {
            String ligne = lignes[i];
            String occupations[] = ligne.split("-");
            if(occupations.length != b.getSize()) return Optional.empty();
            for(int j = 0; j<occupations.length; j++) {
                String occupation = occupations[j];
                if(occupation.equals("○")) putStone(b, new Position(j, noLigneMat), Color.Black);
                else if(occupation.equals("●")) putStone(b, new Position(j, noLigneMat), Color.White);
                else if(!occupation.equals("+")) return Optional.empty();
            }
            noLigneMat++;
        }
        return Optional.of(b);
    }

    private void putStone(Board b, Position p, Color c) {
        Intersection intersection = b.getIntersection(p);
        intersection.setOccupation(c);
    }
}
