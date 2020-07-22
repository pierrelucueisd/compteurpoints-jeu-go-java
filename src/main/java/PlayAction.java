public class PlayAction implements Action {

    Position position;

    public PlayAction(Position position) {
        this.position = position;
    }

    @Override
    public void execute(BoardController bc, Player p) {
        bc.putStoneOnBoard(p.getColor(), position);
    }
}
