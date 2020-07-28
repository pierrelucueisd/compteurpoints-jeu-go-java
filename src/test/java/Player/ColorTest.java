package Player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void getSymbol() {
        Color black = Color.Black;
        Color white = Color.White;

        String blackSymbol = black.getSymbol();
        String whiteSymbol = white.getSymbol();

        assertEquals(blackSymbol, "○");
        assertEquals(whiteSymbol, "●");
    }
}