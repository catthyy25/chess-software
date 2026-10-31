package software.ulpgc.gameservice.application.ports;

import software.ulpgc.gameservice.application.domain.model.Board;

public interface BoardDeserializer {
    Board deserialize(String value);
}
