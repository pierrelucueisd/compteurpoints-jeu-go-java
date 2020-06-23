public class Player {
    private final Color color;
    private boolean hasPassed;

    public Player(Color color) {
        this.color = color;
        this.hasPassed = false;
    }

    public Color getColor() {
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
