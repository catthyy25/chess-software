package software.ulpgc.gameservice.infrastructure.adapters;

import software.ulpgc.gameservice.architecture.model.Position;
import software.ulpgc.gameservice.architecture.ports.BoardDeserializer;
import software.ulpgc.gameservice.architecture.ports.PositionDeserializer;
import software.ulpgc.gameservice.architecture.ports.StateDeserializer;

public class FenPositionDeserializer implements PositionDeserializer {
    private final BoardDeserializer boardDeserializer;
    private final StateDeserializer stateDeserializer;

    public FenPositionDeserializer(BoardDeserializer boardDeserializer, StateDeserializer stateDeserializer) {
        this.boardDeserializer = boardDeserializer;
        this.stateDeserializer = stateDeserializer;
    }

    @Override
    public Position deserialize(String value) {
        return new Position(boardDeserializer.deserialize(value), stateDeserializer.deserialize(value));
    }
}