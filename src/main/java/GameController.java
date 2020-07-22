import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        ErrorObservable obs = ErrorObservable.getSingleton();
        List<ErrorObserver> observers = Stream.of(carousel, gameConsole).collect(Collectors.toList());
        observers.forEach(obs::attach);

        while (!allPlayerHavePassed(players) && scanner.hasNext()) {
            Player p = carousel.getCurrentPlayer();
            playTurn(scanner, p);
            carousel.nextTurn();
        }
        gameConsole.printBoard(getBoardToString());
        observers.forEach(obs::detach);
    }

    public String getBoardToString() {
        return boardController.getCurrentBoard().toString();
    }

    private void playTurn(Scanner scanner, Player p) {
        p.resetPass();
        Optional<Action> action;
        do {
            action = gameConsole.readAction(scanner.next());
            action.ifPresent(a -> a.execute(boardController, p));
        } while(!action.isPresent() && scanner.hasNext());
    }

    private boolean allPlayerHavePassed(List<Player> players) {
        return players.stream().allMatch(Player::hasPassed);
    }
}
