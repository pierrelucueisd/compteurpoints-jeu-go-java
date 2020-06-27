import java.util.Objects;
import java.util.Optional;

public class Intersection {
    private final Position position;
    private Optional<Color> occupation;

    public Intersection(Position position) {
        this.position = position;
        this.occupation = Optional.empty();
    }

    public void setOccupation(Optional<Color> occupation) {
        this.occupation = occupation;
    }

    public Position getPosition() {
        return position;
    }

    public Optional<Color> getOccupation() {
        return occupation;
    }

    public boolean isVacant() {
        return !occupation.isPresent();
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

    public Intersection(Intersection other) {
        this.position = new Position(other.position);
        if(other.occupation.isPresent()) {
            this.occupation = Optional.of(other.occupation.get());
        }else {
            this.occupation = Optional.empty();
        }
    }
}
