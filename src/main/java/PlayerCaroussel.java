import java.util.ArrayList;
import java.util.List;

public class PlayerCaroussel {
    int nbIter;
    ArrayList<Player> players;
    public PlayerCaroussel(List<Player> pl) {
        this.players = (ArrayList<Player>)pl;
        nbIter = 0;
    }

    public void nextTurn(){
        nbIter++;
    }

    public Player getCurrentPlayer() {
        return players.get((nbIter-1) % players.size());
    }

    public Player getTurnPlayer(int turnNb) {
        return players.get((turnNb-1) % players.size());
    }

    public int getCurrentTurnNumber() {
        return nbIter;
    }

}
