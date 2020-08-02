package Player;

public enum Color {
    Black("○", "Black"),
    White("●", "white");

    private final String symbol;
    private final String name;

    Color(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }


    @Override
    public String toString() {
        return name;
    }
}
