import java.util.Objects;

public class Intersection {
    private final Position position;
    private Color stone;
    private boolean vacancy;

    public Intersection(Position position) {
        this.position = position;
        this.stone = null;
        this.vacancy = true;
    }

    public Color getStone() {
        return stone;
    }

    public void setStone(Color stone) {
        this.stone = stone;
        vacancy = false;
    }

    public void removeStone() {
        stone = null;
        vacancy = true;
    }

    public boolean hasPosition(Position p) {
        return position.equals(p);
    }

    public boolean isVacant() {
        return vacancy;
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
