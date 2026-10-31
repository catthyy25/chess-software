package software.ulpgc.gameservice.infrastructure.adapters;

import software.ulpgc.gameservice.application.domain.model.Board;
import software.ulpgc.gameservice.application.domain.model.Piece;
import software.ulpgc.gameservice.application.domain.model.Square;
import software.ulpgc.gameservice.application.ports.BoardDeserializer;

import java.util.HashMap;
import java.util.Map;

public class FenBoardDeserializer implements BoardDeserializer {

    @Override
    public Board deserialize(String value) {
        return deserialize(value.split(" ")[0].split("/"));
    }

    private Board deserialize(String[] ranks) {
        return new Board(piecesIn(ranks));
    }

    private Map<Square, Piece> piecesIn(String[] ranks) {
        Map<Square, Piece> map = new HashMap<>();
        int j = 7;
        for (String rank: ranks) {
            map.putAll(piecesIn(j--, rank.toCharArray()));
        }
        return map;
    }

    private Map<Square, Piece> piecesIn(int j, char[] rank) {
        Map<Square, Piece> map = new HashMap<>();
        int i = 0;
        for (char symbol : rank) {
            if (Character.isDigit(symbol)) i += symbol - '0';
            else map.put(Square.of(i++, j), pieceOf(symbol));
        }
        return map;
    }

    private static final Map<Character, Piece> PIECES = Map.ofEntries(
            Map.entry('K', Piece.WhiteKing),
            Map.entry('Q', Piece.WhiteQueen),
            Map.entry('R', Piece.WhiteRook),
            Map.entry('B', Piece.WhiteBishop),
            Map.entry('N', Piece.WhiteKnight),
            Map.entry('P', Piece.WhitePawn),
            Map.entry('k', Piece.BlackKing),
            Map.entry('q', Piece.BlackQueen),
            Map.entry('r', Piece.BlackRook),
            Map.entry('b', Piece.BlackBishop),
            Map.entry('n', Piece.BlackKnight),
            Map.entry('p', Piece.BlackPawn)
    );

    private Piece pieceOf(char symbol) {
        Piece piece = PIECES.get(symbol);
        if (piece == null) {
            throw new IllegalArgumentException("Invalid FEN symbol: " + symbol);
        }
        return piece;
    }
}
