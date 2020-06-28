public class Player {
    private final Color color;
    private boolean hasPassed;

    public Player(Color color) {
        this.color = color;
        this.hasPassed = false;
    }

    public Player(Player p) {
        this.color = p.color;
        this.hasPassed = p.hasPassed;
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

    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                '}';
    }
}
