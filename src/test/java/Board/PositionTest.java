package Board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Board board = new Board(9);

    @Test
    void isValid() {
        Position pos = new Position(0, 0);
        assertTrue(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowX() {
        Position pos = new Position(9, 0);
        assertFalse(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowY() {
        Position pos = new Position(0, 9);
        assertFalse(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowXY() {
        Position pos = new Position(9, 9);
        assertFalse(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowXMinus() {
        Position pos = new Position(-1, 0);
        assertFalse(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowYMinus() {
        Position pos = new Position(0, -1);
        assertFalse(pos.isValid(board));
    }

    @Test
    void isInvalidOverflowXYMinus() {
        Position pos = new Position(-1, -1);
        assertFalse(pos.isValid(board));
    }

    @Test
    void testEquals() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 0);

        assertEquals(pos, pos2);
    }

    @Test
    void testNotEquals() {
        Position pos = new Position(0, 1);
        Position pos2 = new Position(1, 0);

        assertNotEquals(pos, pos2);
    }
}