package Game;

import Action.Action;
import Action.PlayAction;
import Action.PlayOnBoardAction;
import Action.PassAction;
import Board.Deserializer;
import Board.Position;
import Board.PositionDeserializer;
import PointCalculator.PlayersStats.PlayersScoreStats;

import java.util.Optional;



public class GameConsole implements ErrorObserver {

    public Optional<Action> readAction(String input){
        Deserializer<Position> pd = new PositionDeserializer();
        if (input.toLowerCase().equals("pass"))
            return Optional.of(new PassAction());
        Optional<Position> pos = pd.deserialize(input);
        return pos.map(p -> new PlayOnBoardAction(new PlayAction(p), p));
    }

    public void printBoard(String board) {
        System.out.println(board);
    }

    public void promptActionMessage(){
        System.out.println("Enter your next move, you can write \"PASS\" or a position like \"B3 or F8\":");
    }

    public void printScore(PlayersScoreStats stats){
        int black = stats.getBlackPoints();
        int white = stats.getWhitePoints();

        String str =
                "----------------\n" +
                "     SCORE      \n" +
                "----------------\n" +
                "Black: " + black + " points\n" +
                "White: " + white + " points";

        System.out.println(str);
        int winner = black - white;
        if(winner > 0){
            System.out.println("\nBLACK WINS!!!");
        }else if(winner < 0){
            System.out.println("\nWHITE WINS!!!");
        }else{
            System.out.println("\nIT'S A TIE!!!");
        }
    }

    @Override
    public void update(ErrorType err) {
        switch (err){
            case InvalidPosition:
                System.out.println("The entered position is invalid.");
                break;
            case Suicide:
                System.out.println("The entered position is a suicide move.");
                break;
            case IntersectionTaken:
                System.out.println("The entered position is already taken.");
                break;
            case Ko:
                System.out.println("The entered position is refused because of the eternity rule(Ko).");
        }
    }
}
