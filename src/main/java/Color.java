import java.util.Arrays;

public enum Color {
    None("+"),
    Black("*"),
    White("o");

    private final String symbol;

    Color(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
