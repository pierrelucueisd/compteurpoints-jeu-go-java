import java.util.Optional;

public class GameConsole {

    public Optional<Action> readAction(String input, Player p){
        Deserializer<Position> pd = new PositionDeserializer();
        if (input.toLowerCase().equals("pass"))
            return Optional.of(new PassAction());
        Optional<Position> pos = pd.deserialize(input);
        if(!pos.isPresent())
            return Optional.empty();
        else
            return Optional.of(new PutStoneAction(pos));
    }

    public void printBoard(String board) {
        System.out.println(board);
    }

    public void printResultError(ErrorType type) {
        switch (type){
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
