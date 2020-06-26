import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    Board board;
    List<Player> players;
    GameConsole gameConsole;

    public GameController(int size) {
        this.board = new Board(size);
        this.gameConsole = new GameConsole();
        this.players = Arrays.stream(Color.values())
                .filter(c -> c != Color.None)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void startGame (String input) {
        List<Action> readActions = gameConsole.readActions(input);
        Collections.reverse(readActions);
        Stack<Action> actions = new Stack<>();
        actions.addAll(readActions);
        while (!allPlayersHavePassed() && !actions.empty())
            playTurn(actions);
        endGame();
    }

    private void playTurn(Stack<Action> actions) {
        for (Player p: players) {
            p.resetPassState();
            boolean isAllowed = false;
            while (!isAllowed && !actions.empty()) {
                Action chosenAction = actions.pop();
                isAllowed = chosenAction.isAllowed(board, p);
                if (isAllowed)
                    chosenAction.execute(board, p);
                else
                    gameConsole.printResultError(chosenAction.getError());
            }
        }
    }

    private void endGame() {
        board.removeDeadStone();
        gameConsole.printBoard(board.toString());
    }

    private boolean allPlayersHavePassed() {
        return players.stream().allMatch(Player::hasPassed);
    }
}
