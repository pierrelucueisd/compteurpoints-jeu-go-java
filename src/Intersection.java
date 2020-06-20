public class Intersection {
    private final Position position;
    private boolean koActived;
    private PlayerColor stone;

    public Intersection(Position position) {
        this.position = position;
        this.koActived = false;
        this.stone = null;
    }

    public boolean isKoActived() {
        return koActived;
    }

    public PlayerColor getStone() {
        return stone;
    }

    public void setKoActived(boolean koActived) {
        this.koActived = koActived;
    }

    public void setStone(PlayerColor stone) {
        this.stone = stone;
    }

    public boolean hasPosition(Position p) {
        return position.equals(p);
    }

    public boolean isFree() {
        return stone == null;
    }
}
