import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private Board board;
    private List<Player> players;
    private GameConsole gameConsole;
    private ArrayList<ActionType> logActionTypes;
    private PlayerCaroussel caroussel;


    public GameController(int size) {
        this.board = new Board(size);
        logActionTypes = new ArrayList<ActionType>();
        this.gameConsole = new GameConsole();
        this.players = Arrays.stream(Color.values())
                .filter(c -> c != Color.None)
                .map(Player::new)
                .collect(Collectors.toList());
        this.caroussel = new PlayerCaroussel(this.players);
    }

    public boolean actionTypeLogHaveTwoLasPass() {
        int taille = logActionTypes.size();
        return taille >= 2
                && logActionTypes.get(taille-1)== ActionType.Pass
                && logActionTypes.get(taille-2)== ActionType.Pass;
    }

    public void startGame () throws Exception {
        while(!actionTypeLogHaveTwoLasPass()) {
            caroussel.nextTurn();
            Player p = caroussel.getCurrentPlayer();
            Action chosenAction = gameConsole.promptAction(p);
            while(!chosenAction.isAllowed(board, p)) {
                GameConsole.printResultError(chosenAction.getError());
                chosenAction = gameConsole.promptAction(p);
            }
            chosenAction.execute(board, p);
            logActionTypes.add(chosenAction.getType());
        }
        endGame();
    }

    private void endGame() {
        board.removeDeadStone();
        gameConsole.printBoard(board.toString());
    }
}
