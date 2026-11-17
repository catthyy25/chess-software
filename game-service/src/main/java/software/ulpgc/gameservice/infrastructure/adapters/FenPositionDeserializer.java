package software.ulpgc.gameservice.infrastructure.adapters;

import software.ulpgc.gameservice.architecture.model.CastlingRight;
import software.ulpgc.gameservice.architecture.model.Position;
import software.ulpgc.gameservice.architecture.model.State;
import software.ulpgc.gameservice.architecture.model.board.Color;
import software.ulpgc.gameservice.architecture.ports.BoardDeserializer;
import software.ulpgc.gameservice.architecture.ports.PositionDeserializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FenPositionDeserializer implements PositionDeserializer {
    private final BoardDeserializer boardDeserializer;

    public FenPositionDeserializer(BoardDeserializer boardDeserializer) {
        this.boardDeserializer = boardDeserializer;
    }

    @Override
    public Position deserialize(String value) {
        return new Position(boardDeserializer.deserialize(value), stateOf(value));
    }

    private State stateOf(String value) {
        String[] fields = value.split(" ");
        return new State(sideOf(fields[1]), castlingRightsOf(fields[2]));
    }

    private Color sideOf(String symbol) {
        return symbol.equals("w") ? Color.White : Color.Black;
    }

    private Set<CastlingRight> castlingRightsOf(String symbols) {
        return symbols.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != '-')
                .map(this::castlingRightOf)
                .collect(Collectors.toSet());
    }

    private CastlingRight castlingRightOf(char symbol) {
        return Arrays.stream(CastlingRight.values())
                .filter(right -> right.symbol() == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid castling symbol: " + symbol));
    }
}