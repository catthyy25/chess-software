package software.ulpgc.gameservice.architecture.model.board;

public enum Piece {
    WhitePawn('P'), WhiteKnight('N'), WhiteBishop('B'), WhiteRook('R'), WhiteQueen('Q'), WhiteKing('K'),
    BlackPawn('p'), BlackKnight('n'), BlackBishop('b'), BlackRook('r'), BlackQueen('q'), BlackKing('k');

    private final char symbol;

    Piece(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }

    public boolean isWhite() {
        return this.name().startsWith("White");
    }

    public boolean isBlack() {
        return this.name().startsWith("Black");
    }

    public boolean isRook() {
        return this.name().endsWith("Rook");
    }

    public boolean isPawn() {
        return this.name().endsWith("Pawn");
    }

    public boolean isKnight() {
        return this.name().endsWith("Knight");
    }

    public boolean isBishop() {
        return this.name().endsWith("Bishop");
    }

    public boolean isQueen() {
        return this.name().endsWith("Queen");
    }

    public boolean isKing() {
        return this.name().endsWith("King");
    }
}