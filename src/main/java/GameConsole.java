import java.util.Optional;

public class GameConsole {

    public Action readAction(String input, GameController gc){
        Deserializer<Position> pd = new PositionDeserializer();
        Optional<Position> vacant = Optional.empty();
        if (input.toLowerCase().equals("pass")) return new Action(ActionType.Pass, vacant, gc);
        Optional<Position> pos = pd.deserialize(input);
        if(!pos.isPresent())
            return new Action(ActionType.Invalid, vacant, gc);
        else
            return new Action(ActionType.Play, pos, gc);
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
