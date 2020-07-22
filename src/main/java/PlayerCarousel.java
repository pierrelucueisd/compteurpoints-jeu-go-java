import java.util.List;

public class PlayerCarousel implements ErrorObserver {

    int nbIter;
    final List<Player> players;

    public PlayerCarousel(List<Player> pl) {
        this.players = pl;
        nbIter = 0;
    }

    public void nextTurn(){
        nbIter++;
    }

    public Player getCurrentPlayer() {
        return players.get(nbIter % players.size());
    }

    @Override
    public void update(ErrorType err) {
        // If an error occurs, the current player will have to play again
        nbIter--;
    }
}
