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
