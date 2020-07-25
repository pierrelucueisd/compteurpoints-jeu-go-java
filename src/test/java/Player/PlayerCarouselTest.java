package Player;

import Game.ErrorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCarouselTest {
    PlayerCarousel carousel;

    @BeforeEach
    void setUp() {
        List<Player> players = Arrays.stream(Color.values())
                .map(Player::new)
                .collect(Collectors.toList());
        carousel = new PlayerCarousel(players);
    }

    @Test
    void nextTurn() {
        int currentIter = carousel.nbIter;
        carousel.nextTurn();

        assertEquals(carousel.nbIter, currentIter + 1);
    }

    @Test
    void getCurrentPlayer() {
        Player p = carousel.getCurrentPlayer();
        carousel.nextTurn();
        carousel.nextTurn();

        assertEquals(p, carousel.getCurrentPlayer());
    }

    @Test
    void update() {
        int currentIter = carousel.nbIter;
        carousel.update(ErrorType.Suicide);

        assertEquals(carousel.nbIter, currentIter - 1);
    }
}