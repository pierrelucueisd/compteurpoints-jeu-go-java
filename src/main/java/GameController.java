import org.javatuples.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameController {

    Board board;
    List<Player> players;

    public GameController(int size) {
        this.board = new Board(size);
        this.players = Arrays.stream(PlayerColor.values())
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void startGame () {
        while (!allPlayersHavePassed())
            playTurn();
        board.removeDeadStone();
        GameConsole.printWinner(getWinner());
        endGame();
    }

    private Player getWinner() {
        return Objects.requireNonNull(players.stream()
                .map(p -> new Pair<>(p, board.calculateScore(p.getColor())))
                .max(Comparator.comparing(Pair::getValue1))
                .orElse(null))
                .getValue0();
    }

    private void playTurn() {
        for (Player p: players) {
            Result res;
            do {
                Action chosenAction = GameConsole.promptAction(p, ActionType.values());
                Position pos = chosenAction.hasType(ActionType.Play) ?
                        GameConsole.promptPosition() : new Position();
                res = chosenAction.execute(board, p, pos);
                if (res.failed())
                    GameConsole.printResultError(res.getType());
            } while (res.failed());
            GameConsole.printBoard(board.toString());
        }
    }

    private void endGame() {

    }

    private boolean allPlayersHavePassed() {
        return players.stream().allMatch(Player::hasPassed);
    }
}
