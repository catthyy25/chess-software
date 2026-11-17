package software.ulpgc.gameservice.architecture.ports;

import software.ulpgc.gameservice.architecture.model.Game;

public interface GameDeserializer {
    Game deserialize(String data);
}
