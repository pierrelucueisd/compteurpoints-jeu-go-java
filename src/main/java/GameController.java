import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {

    private final Board board;
    private final GameConsole gameConsole;
    private final BoardController boardController = new BoardController();

    public Board getBoard() {
        return board;
    }

    public GameController(int size) {
        this.board = new Board(size);
        this.gameConsole = new GameConsole();
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void startGame (Scanner scanner) {
        List<Player> players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
        PlayerCarousel carousel = new PlayerCarousel(players);

        while(!bothPlayerHavePassed(players) && scanner.hasNext()) {
            Player p = carousel.getCurrentPlayer();
            playTurn(scanner, p);
            carousel.nextTurn();
        }
        gameConsole.printBoard(board.toString());
    }

    private void playTurn(Scanner scanner, Player p) {
        Optional<Action> action = gameConsole.readAction(scanner.next());
        Optional<ErrorType> error = action.flatMap(a -> a.isAllowed(this, p));
        while((!action.isPresent() || error.isPresent()) && scanner.hasNext()) {
            error.ifPresent(gameConsole::printResultError);
            action = gameConsole.readAction(scanner.next());
            error = action.flatMap(a -> a.isAllowed(this, p));
        }

        action.ifPresent(a -> a.execute(this, p));
    }

    private boolean bothPlayerHavePassed(List<Player> players) {
        return players.stream().allMatch(Player::hasPassed);
    }
}
