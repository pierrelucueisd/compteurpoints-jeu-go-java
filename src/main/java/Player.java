public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Player(Player other) {
        this.color = other.color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                '}';
    }
}
