import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {

    private final GameConsole gameConsole;
    private final BoardController boardController;

    public GameController(int size) {
        this.boardController = new BoardController(size);
        this.gameConsole = new GameConsole();
    }

    public void startGame (Scanner scanner) {
        List<Player> players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
        PlayerCarousel carousel = new PlayerCarousel(players);

        while(!allPlayerHavePassed(players) && scanner.hasNext()) {
            Player p = carousel.getCurrentPlayer();
            playTurn(scanner, p);
            carousel.nextTurn();
        }
        gameConsole.printBoard(getBoardToString());
    }

    public String getBoardToString() {
        return boardController.getCurrentBoard().toString();
    }

    private void playTurn(Scanner scanner, Player p) {
        p.resetPass();
        Optional<Action> action = gameConsole.readAction(scanner.next());
        Optional<ErrorType> error = action.flatMap(a -> a.isAllowed(boardController, p));
        while((!action.isPresent() || error.isPresent()) && scanner.hasNext()) {
            error.ifPresent(gameConsole::printResultError);
            action = gameConsole.readAction(scanner.next());
            error = action.flatMap(a -> a.isAllowed(boardController, p));
        }

        action.ifPresent(a -> a.execute(boardController, p));
    }

    private boolean allPlayerHavePassed(List<Player> players) {
        return players.stream().allMatch(Player::hasPassed);
    }
}
