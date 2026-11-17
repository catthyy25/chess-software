package software.ulpgc.gameservice.architecture.model;

public enum CastlingRight {
    WHITE_KING_SIDE('K'), WHITE_QUEEN_SIDE('Q'),
    BLACK_KING_SIDE('k'), BLACK_QUEEN_SIDE('q');

    private final char symbol;

    CastlingRight(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }
}