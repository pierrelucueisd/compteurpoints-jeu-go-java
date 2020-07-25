package Board;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PositionDeserializerTest {
    PositionDeserializer pd = new PositionDeserializer();

    @Test
    void deserializeValidPosition() {
        Position expected = new Position(0, 0);

        assertPosition(pd.deserialize("A1"), expected);
    }

    @Test
    void deserializeValidPositionWithLowerCase() {
        Position expected = new Position(0, 0);

        assertPosition(pd.deserialize("a1"), expected);
    }

    @Test
    void deserializeValidPositionWithLetterJ() {
        // 0 1 2 3 4 5 6 7 8
        // A B C D E F G H J
        Position expected = new Position(8, 0);

        assertPosition(pd.deserialize("J1"), expected);
    }

    @Test
    void deserializeInvalidPositionTwoNumbers() {
        Optional<Position> pos = pd.deserialize("11");
        if (pos.isPresent())
            fail();
    }

    @Test
    void deserializeInvalidPositionTwoLetters() {
        Optional<Position> pos = pd.deserialize("AA");
        if (pos.isPresent())
            fail();
    }

    @Test
    void deserializeInvalidPositionInverted() {
        Optional<Position> pos = pd.deserialize("1A");
        if (pos.isPresent())
            fail();
    }

    @Test
    void deserializeInvalidPositionWithZero() {
        Optional<Position> pos = pd.deserialize("A0");
        if (pos.isPresent())
            fail();
    }

    void assertPosition(Optional<Position> pos, Position expected) {
        if (pos.isPresent())
            assertEquals(pos.get(), expected);
        else
            fail();
    }
}