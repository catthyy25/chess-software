package software.ulpgc.gameservice.infrastructure.adapters.serialization;

import software.ulpgc.gameservice.architecture.model.Board;
import software.ulpgc.gameservice.architecture.model.board.Piece;
import software.ulpgc.gameservice.architecture.model.board.Square;
import software.ulpgc.gameservice.architecture.ports.BoardDeserializer;

import java.util.Arrays;
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
        for (String rank : ranks) {
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

    private Piece pieceOf(char symbol) {
        return Arrays.stream(Piece.values())
                .filter(piece -> piece.symbol() == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid FEN symbol: " + symbol));
    }
}