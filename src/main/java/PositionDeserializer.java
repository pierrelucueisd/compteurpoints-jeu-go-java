import java.util.Optional;

public class PositionDeserializer implements Deserializer<Position> {

    @Override
    public Optional<Position> deserialize(String p) {
        return Optional.empty();
    }

    @Override
    public boolean validate(Position position) {
        return false;
    }


}
