package Board;

import java.util.Objects;

public class Position {
    private final Integer x;
    private final Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = -1;
        this.y = -1;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public boolean isValid(BoardInterface b) {
        int size = b.getSize();
        return x < size && y < size && x >= 0 && y >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x.equals(position.x) &&
                y.equals(position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Board.Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position(Position other) {
        this.x = other.x;
        this.y = other.y;
    }
}
