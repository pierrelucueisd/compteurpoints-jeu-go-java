package Board.Builder;

import Board.Board;
import Board.Position;
import Player.Color;

import java.util.ArrayList;
import java.util.Optional;

public class BoardBuilderFromColorStrings implements BoardBuilder{
    String noirs;
    String blancs;

    public BoardBuilderFromColorStrings(String _noirs, String _blancs) {
        this.noirs = _noirs;
        this.blancs = _blancs;
    }

    public Optional<Board> build() {
        Board b = new Board(9);
        Optional<ArrayList<Position>> optPos;
        optPos = stringToPositions(noirs, b);
        if(!optPos.isPresent()) return Optional.empty();
        for(Position pos: optPos.get()) b.putStone(Color.Black, pos);

        optPos = stringToPositions(blancs, b);
        if(!optPos.isPresent()) return Optional.empty();
        for(Position pos: optPos.get()) b.putStone(Color.White, pos);

        return Optional.of(b);
    }

    private static Optional<ArrayList<Position>> stringToPositions(String str, Board b) {
        ArrayList<Position> positions = new ArrayList<Position>();
        String[] tab = str.split("\\s+");
        if(tab.length == 1 && tab[0].equals("")) return Optional.of(positions);
        for(String val: tab) {
            Optional<Position> optP = stringToPosition(val, b);
            if(!optP.isPresent()) return Optional.empty();
            positions.add(optP.get());
        }
        return Optional.of(positions);
    }

    private static Optional<Position> stringToPosition(String str, Board b) {
        if(str.length() != 2) return Optional.empty();
        int posLeft = Character.getNumericValue(str.charAt(0));
        int postRight = Character.getNumericValue(str.charAt(0));
        if(posLeft < 0 || postRight < 0) return Optional.empty();
        Position p = new Position(posLeft, postRight);
        if(!p.isValid(b)) return Optional.empty();
        return Optional.of(p);
    }
}
