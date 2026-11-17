package software.ulpgc.gameservice.architecture.ports;

import software.ulpgc.gameservice.architecture.model.Board;

public interface BoardDeserializer {
    Board deserialize(String value);
}
