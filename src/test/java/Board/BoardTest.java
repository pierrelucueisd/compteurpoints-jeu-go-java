package Board;

import Player.Color;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void isGroupPrisoner() {
        // w b .     w b .
        // w b . =>  w b .
        // . . .     b . .

        Board b = new Board(9);
        b.putStone(Color.Black, new Position(0, 1));
        b.putStone(Color.Black, new Position(1, 1));
        b.putStone(Color.White, new Position(0, 0));
        b.putStone(Color.White, new Position(1, 0));

        Intersection i = b.getIntersection(2, 0);
        i.setOccupation(Color.Black);
        List<Intersection> whiteGroup = b.getStoneGroup(b.getIntersection(0, 0));
        assertTrue(b.isGroupPrisoner(whiteGroup));
    }

    @Test
    void getStoneGroup() {
        // . b .
        // b b b
        // . b .

        Board b = new Board(9);

        Position pos = new Position(0, 1);
        Position pos2 = new Position(1, 1);
        Position pos3 = new Position(1, 0);
        Position pos4 = new Position(2, 1);
        Position pos5 = new Position(1, 2);

        b.putStone(Color.Black, pos);
        b.putStone(Color.Black, pos2);
        b.putStone(Color.Black, pos3);
        b.putStone(Color.Black, pos4);
        b.putStone(Color.Black, pos5);

        List<Intersection> group = b.getStoneGroup(b.getIntersection(pos2));
        List<Intersection> expected = Stream.of(pos, pos2, pos3, pos4, pos5)
                .map(b::getIntersection)
                .collect(Collectors.toList());

        expected.forEach(i -> assertTrue(group.contains(i)));

    }

    @Test
    void removePrisoners() {
        // w b      w b      . b
        // . .  =>  b .  =>  b .

        Board b = new Board(9);
        b.putStone(Color.White, new Position(0, 0));
        b.putStone(Color.Black, new Position(0, 1));
        Intersection i = b.getIntersection(1, 0);
        i.setOccupation(Color.Black);

        b.removePrisoners(i);
        assertTrue(b.getIntersection(0, 0).isVacant());
    }

    @Test
    void testEquals() {
        Board b = new Board(9);
        Board b2 = new Board(9);

        b.putStone(Color.Black, new Position(1, 1));
        b2.putStone(Color.Black, new Position(1, 1));

        assertEquals(b, b2);
    }

    @Test
    void testNotEquals() {
        Board b = new Board(9);
        Board b2 = new Board(9);

        b.putStone(Color.Black, new Position(1, 1));
        b2.putStone(Color.White, new Position(1, 1));

        assertNotEquals(b, b2);
    }
}