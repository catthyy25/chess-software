package software.ulpgc.gameservice.architecture.ports;

import software.ulpgc.gameservice.architecture.model.Position;

public interface PositionDeserializer {
    Position deserialize(String text);
}
