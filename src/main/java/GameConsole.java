import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameConsole {

    public List<Action> readActions(String input) {
        List<String> inputAction = Arrays.asList(input.split(" "));
        return inputAction.stream()
                .map(this::readAction)
                .collect(Collectors.toList());
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

    private Action readAction(String input){
        Deserializer<Position> pd = new PositionDeserializer();
        if (input.toLowerCase().equals("pass")) return new Action(ActionType.Pass, null);
        Optional<Position> pos = pd.deserialize(input);
        return pos.map(position -> new Action(ActionType.Play, position)).orElseGet(() -> new Action(ActionType.Invalid, null));
    }
}
