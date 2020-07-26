package PointCalculator;

import Board.Board;
import Board.Intersection;
import Board.Builder.BoardBuilder;
import Board.Builder.BoardBuilderFromBoardRepresentation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncircledAreaFetcherTest {

    @Test
    void fetch() {
        String representation =
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "+-+-●-●-●-●-+-+-+\n" +
                "+-+-●-+-+-●-+-+-+\n" +
                "+-+-●-+-+-+-●-+-+\n" +
                "+-+-+-●-●-●-+-+-+\n" +
                "+-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n" +
                "●-+-+-+-+-+-+-+-+\n";
        BoardBuilder builder = new BoardBuilderFromBoardRepresentation(representation);
        Optional<Board> optB = builder.build();
        assertTrue(optB.isPresent());
        Board b = optB.get();
        System.out.println(b);
        EncircledAreaFetcher encircledAreaFetcher = new EncircledAreaFetcher(b);
        encircledAreaFetcher.fetch();
        int y = 9;
    }
}