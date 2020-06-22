public class Player {
    private final PlayerColor color;
    private boolean hasPassed;

    public Player(PlayerColor color) {
        this.color = color;
        this.hasPassed = false;
    }

    public PlayerColor getColor() {
        return color;
    }

    public boolean hasPassed() {
        return hasPassed;
    }

    public void pass() {
        this.hasPassed = true;
    }

    public void resetPassState() {
        this.hasPassed = false;
    }
}
