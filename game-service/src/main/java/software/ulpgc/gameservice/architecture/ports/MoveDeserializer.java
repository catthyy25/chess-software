package software.ulpgc.gameservice.architecture.ports;

import software.ulpgc.gameservice.architecture.model.Move;

public interface MoveDeserializer {
    Move deserialize(String value);
}
