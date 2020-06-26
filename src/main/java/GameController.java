import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private final Board board;
    private final GameConsole gameConsole;
    private final ArrayList<ActionType> logActionTypes;
    private final PlayerCarousel carousel;

    public Board getBoard() {
        return board;
    }

    public GameController(int size) {
        this.board = new Board(size);
        logActionTypes = new ArrayList<>();
        this.gameConsole = new GameConsole();
        List<Player> players = Arrays.stream(Color.values())
                .filter(c -> c != Color.None)
                .map(Player::new)
                .collect(Collectors.toList());
        this.carousel = new PlayerCarousel(players);
    }

    public boolean actionTypeLogHaveTwoLasPass() {
        int logSize = logActionTypes.size();
        return logSize >= 2
                && logActionTypes.get(logSize - 1)== ActionType.Pass
                && logActionTypes.get(logSize - 2)== ActionType.Pass;
    }

    public void startGame (Scanner scanner) {
        while(!actionTypeLogHaveTwoLasPass() && scanner.hasNext()) {
            carousel.nextTurn();
            Player p = carousel.getCurrentPlayer();
            Action chosenAction = gameConsole.readAction(scanner.next());
            while(!chosenAction.isAllowed(board, p) && scanner.hasNext()) {
                gameConsole.printResultError(chosenAction.getError());
                chosenAction = gameConsole.readAction(scanner.next());
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
