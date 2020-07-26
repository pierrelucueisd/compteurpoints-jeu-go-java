package Board.Builder;

import Board.Board;
import Board.Position;
import Player.Color;

import java.util.Optional;

public class BoardBuilderFromBoardRepresentation implements BoardBuilder {
    String str;
    public BoardBuilderFromBoardRepresentation(String _str) {
        this.str = _str;
    }

    @Override
    public Optional<Board> build() {
        Board b = new Board(9);
        String[] lignes = str.split("\n");
        if(lignes.length != b.getSize()) return Optional.empty();
        int noLigneMat = 0;
        for(int i = lignes.length-1; i>=0; i--) {
            String ligne = lignes[i];
            String occupations[] = ligne.split("-");
            if(occupations.length != b.getSize()) return Optional.empty();
            for(int j = 0; j<occupations.length; j++) {
                String occupation = occupations[j];
                if(occupation.equals("○")) b.putStone(Color.Black, new Position(j, noLigneMat));
                else if(occupation.equals("●")) b.putStone(Color.White, new Position(j, noLigneMat));
                else if(!occupation.equals("+")) return Optional.empty();
            }
            noLigneMat++;
        }
        return Optional.of(b);
    }
}
