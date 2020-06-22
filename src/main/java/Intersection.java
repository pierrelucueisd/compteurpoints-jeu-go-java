import java.util.Objects;

public class Intersection {
    private final Position position;
    private PlayerColor stone;

    public Intersection(Position position) {
        this.position = position;
        this.stone = null;
    }

    public PlayerColor getStone() {
        return stone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(position, that.position) &&
                stone == that.stone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, stone);
    }
}
