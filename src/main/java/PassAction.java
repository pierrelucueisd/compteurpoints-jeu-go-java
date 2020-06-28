import java.util.Optional;

public class PassAction implements Action {

    public void execute(GameController gc, Player p) { p.pass(); }

    public Optional<ErrorType> isAllowed(GameController gc, Player p) {
        return Optional.empty();
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.empty();
    }
}
