public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    public void pass() {

    }



    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                '}';
    }
}
