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
            Optional<Action> chosenActionT = gameConsole.readAction(scanner.next(), p);
            Optional<ErrorType> error = Optional.empty();
            while(( !chosenActionT.isPresent() || (error = getFirstInvalidityErrorOf(chosenActionT.get())).isPresent())
                    && scanner.hasNext()
            ) {
                error.ifPresent(gameConsole::printResultError);
                chosenActionT = gameConsole.readAction(scanner.next(), p);
            }
            Action action = chosenActionT.get();
            action.execute(board, p);
            logActionTypes.add(action.getType());
            logger.addBoard(new Board(board));
        }
        endGame();
    }

    private void endGame() {
        board.removeDeadStone();
        gameConsole.printBoard(board.toString());
    }

    private Optional<ErrorType> getFirstInvalidityErrorOf(Action actionT) {
        Optional<Position> pos = actionT.getPosition();
        Player p = carousel.getCurrentPlayer();
        if(pos.isPresent()) {
            if(!pos.get().isValid(board))
                return Optional.of(ErrorType.InvalidPosition);
            if (!board.isIntersectionVacant(pos.get()))
                return Optional.of(ErrorType.IntersectionTaken);
//            if(isActionSuicide(actionT, p))
//                return Optional.of(ErrorType.Suicide);
//            if(isActionKo(actionT, p))
//                return Optional.of(ErrorType.Ko);
        }
        return Optional.empty();
    }

    private boolean isActionSuicide(Action actionT, Player p) {
        Board bC = new Board(board);
        Player pC = new Player(p);
        actionT.execute(bC, pC);
        return bC.isSuicide(actionT.getPosition().get());
    }

    private boolean isActionKo(Action actionT, Player p) {
        Board bC = new Board(board);
        Player pl = new Player(p);
        actionT.execute(bC, pl);
        Optional<Board> lastBoard = logger.getLastBoard();
        return lastBoard.map(value -> value.equals(bC)).orElse(false);
    }
}
