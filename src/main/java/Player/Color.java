package Player;

public enum Color {
    Black("○"),
    White("●");

    private final String symbol;

    Color(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
