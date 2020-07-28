package Board;

import java.util.Optional;

public class PositionDeserializer implements Deserializer<Position> {

    @Override
    public Optional<Position> deserialize(String input) {
        String pos = input.toLowerCase();
        if(pos.length() == 2 && Character.isAlphabetic(pos.charAt(0)) && Character.isDigit(pos.charAt(1))){
            int ASCII_LOWERCASE_A_VALUE = 97;
            char c = pos.charAt(0);
            c = c == 'j' ? 'i' : c; // Letter I is replace by letter J to avoid confusion
            int posX = c - ASCII_LOWERCASE_A_VALUE;
            int posY = Character.getNumericValue(pos.charAt(1)) - 1; // Index start at 0
            return posY < 0 ? Optional.empty() : Optional.of(new Position(posX, posY));
        }
        return Optional.empty();
    }

}
