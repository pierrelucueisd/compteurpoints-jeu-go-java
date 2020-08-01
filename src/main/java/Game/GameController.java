package Game;

import Action.Action;
import Board.Board;
import Board.BoardController;
import Board.IBoardController;
import Board.LecteurEntree.LecteurEntree;
import Player.*;
import PointCalculator.BoardPointCalculator;
import PointCalculator.BoardPointCalculatorImpl;
import PointCalculator.EncircledArea.Validator.RootValidator;
import PointCalculator.EncircledArea.Validator.TakableValidatorNaive;
import PointCalculator.PlayersStats.PlayersScoreStats;
import PointCalculator.visitor.PointCalculatorVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {

    private final GameConsole gameConsole;
    private final IBoardController boardController;
    private LecteurEntree lecteur;

    public GameController(int size, LecteurEntree lecteur) {
        this.boardController = new BoardController(size);
        this.gameConsole = new GameConsole();
        this.lecteur = lecteur;
    }

    public void startGame () {
        List<Player> players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
        PlayerCarousel carousel = new PlayerCarousel(players);

        ErrorObservable obs = ErrorObservable.getSingleton();
        List<ErrorObserver> observers = Stream.of(carousel, gameConsole).collect(Collectors.toList());
        observers.forEach(obs::attach);

        while (!allPlayerHavePassed(players) && this.lecteur.hasNext()) {
            Player p = carousel.getCurrentPlayer();
            //Testing the msg wont be placed here
            gameConsole.promptActionMessage();
            playTurn(lecteur, p);
            carousel.nextTurn();
        }
        // Needs to be print for every move when scanner is from System.in
        gameConsole.printBoard(getBoardToString());

        // Will be in main eventually
        gameOver();

        observers.forEach(obs::detach);
    }

    public void gameOver(){
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

    private void playTurn(LecteurEntree scanner, Player p) {
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
