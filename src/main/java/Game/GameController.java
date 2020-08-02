package Game;

import Action.Action;
import Board.Board;
import Board.BoardController;
import Board.IBoardController;
import Player.*;
import PointCalculator.BoardPointCalculator;
import PointCalculator.BoardPointCalculatorImpl;
import PointCalculator.EncircledArea.Validator.RootValidator;
import PointCalculator.EncircledArea.Validator.TakableValidatorNaive;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {

    private final GameConsole gameConsole;
    private final IBoardController boardController;
    private final ErrorObservable observable;

    public GameController(int size) {
        this.boardController = new BoardController(size);
        this.gameConsole = new GameConsole();
        this.observable = new ErrorObservable();

    }

    public void startGame (IDualScanner scanner) {
        List<Player> players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
        PlayerCarousel carousel = new PlayerCarousel(players);

        List<ErrorObserver> observers = Stream.of(carousel, gameConsole).collect(Collectors.toList());
        observers.forEach(observable::attach);

        while (!allPlayerHavePassed(players)) {
            Player p = carousel.getCurrentPlayer();
            gameConsole.promptActionMessage(p);
            playTurn(scanner, p);
            carousel.nextTurn();
        }
        observers.forEach(observable::detach);

        endGame();
    }

    public void endGame(){
        Board b = new Board(boardController.getCurrentBoard());
        BoardPointCalculator boardPointsCalculator = new BoardPointCalculatorImpl(
                b,
                new RootValidator(b),
                new TakableValidatorNaive(),
                new TakableValidatorNaive()
        );
        gameConsole.printScore(boardPointsCalculator.calculate());
    }

    public String getBoardToString() {
        return boardController.getCurrentBoard().toString();
    }

    private void playTurn(IDualScanner scanner, Player p) {
        p.resetPass();
        Optional<Action> action;
        do {
            action = gameConsole.readAction(scanner.next(), observable);
        } while(!action.isPresent());
        action.get().execute(boardController, p);
        gameConsole.printBoard(getBoardToString());
    }

    private boolean allPlayerHavePassed(List<Player> players) {
        return players.stream().allMatch(Player::hasPassed);
    }
}
