package software.ulpgc.gameservice.architecture.ports;

import software.ulpgc.gameservice.architecture.model.State;

public interface StateDeserializer {
    State deserialize(String value);
}