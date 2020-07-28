package Board;

import Player.Color;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection {
    private final Position position;
    private Color occupation;

    public Intersection(Position position) {
        this.position = position;
    }

    public Intersection(Intersection i) {
        this.position = new Position(i.position);
        this.occupation = i.occupation;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void setOccupation(Color occupation) {
        this.occupation = occupation;
    }

    public Optional<Color> getOccupation() {
        return Optional.ofNullable(occupation);
    }

    public boolean isVacant() {
        return occupation == null;
    }

    public boolean isEnemy(Intersection i) {
        return i.occupation != null && i.occupation != occupation;
    }

    public boolean isFriendly(Intersection i) {
        return i.occupation == occupation;
    }

    public List<Intersection> getNeighbors(BoardInterface b) {
        int x = position.getX();
        int y = position.getY();
        return Stream.of(new Position[] {
                new Position(x - 1, y),
                new Position(x, y + 1),
                new Position(x + 1, y),
                new Position(x, y - 1)})
                .filter(p -> p.isValid(b))
                .map(b::getIntersection)
                .collect(Collectors.toList());
    }

    public List<Intersection> getEnemyNeighbors(BoardInterface b) {
        return getNeighbors(b).stream()
                .filter(this::isEnemy)
                .collect(Collectors.toList());
    }

    public List<Intersection> getFriendlyNeighbors(BoardInterface b) {
        return getNeighbors(b).stream()
                .filter(this::isFriendly)
                .collect(Collectors.toList());
    }

    public boolean hasLiberty(BoardInterface b) {
        return getNeighbors(b).stream()
                .anyMatch(Intersection::isVacant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intersection)) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(occupation, that.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, occupation);
    }
}
