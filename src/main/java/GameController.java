import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private final Board board;
    private final GameConsole gameConsole;
    private final ArrayList<ActionType> logActionTypes;
    private final PlayerCarousel carousel;
    private final BoardLogger logger = new BoardLogger();

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
            Optional<ErrorType> error;
            while((error = getFirstInvalidityErrorOf(chosenAction)).isPresent() && scanner.hasNext()) {
                gameConsole.printResultError(error.get());
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

    private Optional<ErrorType> getFirstInvalidityErrorOf(Action action) {
        Optional<Position> pos = action.getPosition();
        Player p = carousel.getCurrentPlayer();
        if(pos.isPresent()) {
            if(!board.isPositionValid(pos.get()))
                return Optional.of(ErrorType.InvalidPosition);
            if (!board.isIntersectionVacant(pos.get()))
                return Optional.of(ErrorType.IntersectionTaken);
            if(isActionSuicide(action, p))
                return Optional.of(ErrorType.Suicide);
            if(board.isKo(pos.get(), p.getColor()))
                return Optional.of(ErrorType.Ko);
        }
        return Optional.empty();
    }

    private boolean isActionSuicide(Action action, Player p) {
        Board bC = board.deepClone();
        Player pl = p.deepClone(p.getColor());
        action.execute(bC, pl);
        Optional<Position> pos = action.getPosition();
        if(!pos.isPresent()) return false;
        return board.isSuicide(pos.get(), pl.getColor());
    }
}
