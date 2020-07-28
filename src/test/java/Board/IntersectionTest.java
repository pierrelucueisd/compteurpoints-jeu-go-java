package Board;

import Player.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(9);
    }

    @Test
    void isVacant() {
        Intersection i = board.getIntersection(0, 0);

        assertTrue(i.isVacant());
    }

    @Test
    void isNotVacant() {
        Position pos = new Position(0, 0);
        board.putStone(Color.Black, pos);
        Intersection i = board.getIntersection(pos);

        assertFalse(i.isVacant());
    }

    @Test
    void isEnemy() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(1, 0);

        board.putStone(Color.Black, pos);
        board.putStone(Color.White, pos2);

        Intersection i = board.getIntersection(pos);
        Intersection i2 = board.getIntersection(pos2);

        assertTrue(i.isEnemy(i2));
    }

    @Test
    void isFriendly() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(1, 0);

        board.putStone(Color.Black, pos);
        board.putStone(Color.Black, pos2);

        Intersection i = board.getIntersection(pos);
        Intersection i2 = board.getIntersection(pos2);

        assertTrue(i.isFriendly(i2));
    }

    @Test
    void getNeighbors() {

        // n . .
        // i n .
        // n . .

        Position pos = new Position(0, 0);
        Position pos2 = new Position(1, 1);
        Position pos3 = new Position(2, 0);

        Intersection i = board.getIntersection(1, 0);
        List<Intersection> neighbors = i.getNeighbors(board);
        List<Intersection> expected = Stream.of(pos, pos2, pos3)
                .map(p -> board.getIntersection(p))
                .collect(Collectors.toList());

        assertEquals(neighbors, expected);
    }

    @Test
    void getEnemyNeighbors() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(1, 1);
        Position pos4 = new Position(1, 0);

        board.putStone(Color.Black, pos);
        board.putStone(Color.Black, pos2);
        board.putStone(Color.White, pos4);

        Intersection i = board.getIntersection(pos4);
        List<Intersection> neighbors = i.getEnemyNeighbors(board);
        List<Intersection> expected = Stream.of(pos, pos2)
                .map(p -> board.getIntersection(p))
                .collect(Collectors.toList());

        assertEquals(neighbors, expected);
    }

    @Test
    void getFriendlyNeighbors() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(2, 0);
        Position pos3 = new Position(1, 0);

        board.putStone(Color.Black, pos);
        board.putStone(Color.Black, pos2);
        board.putStone(Color.Black, pos3);

        Intersection i = board.getIntersection(pos3);
        List<Intersection> neighbors = i.getFriendlyNeighbors(board);
        List<Intersection> expected = Stream.of(pos, pos2)
                .map(p -> board.getIntersection(p))
                .collect(Collectors.toList());

        assertEquals(neighbors, expected);
    }

    @Test
    void hasLiberty() {
        // i n .
        // . . .

        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 1);

        board.putStone(Color.Black, pos);
        board.putStone(Color.Black, pos2);

        Intersection i = board.getIntersection(pos);

        assertTrue(i.hasLiberty(board));
    }

    @Test
    void hasNoLiberty() {
        // i n .
        // n . .

        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 1);
        Position pos3 = new Position(1, 0);

        board.putStone(Color.Black, pos);
        board.putStone(Color.Black, pos2);
        board.putStone(Color.Black, pos3);

        Intersection i = board.getIntersection(pos);

        assertFalse(i.hasLiberty(board));

    }

    @Test
    void testEquals() {
        Position pos = new Position(0, 0);

        board.putStone(Color.Black, pos);

        Intersection i = board.getIntersection(pos);
        Intersection i2 = board.getIntersection(pos);

        assertEquals(i, i2);
    }

    @Test
    void testEqualsWithVacantIntersections() {
        Position pos = new Position(0, 0);

        Intersection i = board.getIntersection(pos);
        Intersection i2 = board.getIntersection(pos);

        assertEquals(i, i2);

    }
}