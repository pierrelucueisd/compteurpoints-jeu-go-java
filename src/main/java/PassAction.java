import java.util.Optional;

public class PassAction implements Action {

    public PassAction() {}

    public ActionType getType() {
        return ActionType.Pass;
    }

    public Optional<Position> getPosition() {
        return Optional.empty();
    }

    public void execute(Board b, Player p) {}
}
