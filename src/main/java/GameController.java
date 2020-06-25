import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    Board board;
    List<Player> players;
    GameConsole gameConsole;

    public GameController(int size, GameConsole gameConsole) {
        this.gameConsole = gameConsole;
        this.board = new Board(size);
        this.players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void startGame () {
        while (!allPlayersHavePassed())
            playTurn();
        endGame();
    }

    private void playTurn() {
        for (Player p: players) {
            p.resetPassState();
            boolean isAllowed;
            do {
                Action chosenAction = gameConsole.promptAction(p);
                isAllowed = chosenAction.isAllowed(board, p);
                if (isAllowed)
                    chosenAction.execute(board, p);
                else
                    GameConsole.printResultError(chosenAction.getError());
            } while (!isAllowed);
        }
    }

    private void endGame() {
        board.removeDeadStone();
        GameConsole.printBoard(board.toString());
    }

    private boolean allPlayersHavePassed() {
        return players.stream().allMatch(Player::hasPassed);
    }
}
