public class Player {
    private final PlayerColor color;
    private boolean hasPassed;

    public Player(PlayerColor color) {
        this.color = color;
        this.hasPassed = false;
    }

    public void pass() {
        this.hasPassed = true;
    }

    public void resetPassState() {
        this.hasPassed = false;
    }
}
