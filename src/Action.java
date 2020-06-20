public class Action {
    private final ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public Result execute(Board b, Player p, Position pos) {
        return null;
    }
}
