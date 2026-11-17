package software.ulpgc.gameservice.infrastructure.adapters;

import software.ulpgc.gameservice.architecture.model.Game;
import software.ulpgc.gameservice.architecture.ports.GameDeserializer;
import software.ulpgc.gameservice.architecture.ports.PositionDeserializer;

public class FenGameDeserializer implements GameDeserializer {
    private final PositionDeserializer positionDeserializer;

    public FenGameDeserializer(PositionDeserializer positionDeserializer) {
        this.positionDeserializer = positionDeserializer;
    }

    @Override
    public Game deserialize(String data) {
        return new Game(data.lines()
                .filter(line -> !line.isBlank())
                .map(positionDeserializer::deserialize)
                .toList());
    }
}