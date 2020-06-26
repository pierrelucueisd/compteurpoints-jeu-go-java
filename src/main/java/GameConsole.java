import java.util.Optional;

public class GameConsole {

    public Action readAction(String input){
        Deserializer<Position> pd = new PositionDeserializer();
        Optional<Position> vacant = Optional.empty();
        if (input.toLowerCase().equals("pass")) return new PassAction(ActionType.Pass, vacant);
        Optional<Position> pos = pd.deserialize(input);
        if(!pos.isPresent())
            return new PassAction(ActionType.Invalid, vacant); //@todo éventiellement mettre un optional ret readAction
        else
            return new PutStoneAction(ActionType.Play, pos);
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
