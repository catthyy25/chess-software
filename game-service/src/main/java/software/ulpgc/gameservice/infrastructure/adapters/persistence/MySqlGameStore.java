package software.ulpgc.gameservice.infrastructure.adapters.persistence;

import software.ulpgc.gameservice.architecture.ports.GameStore;

public class MySqlGameStore implements GameStore {
    @Override
    public String load(String id) {
        return null;
    }

    @Override
    public void save(String id, String game) {

    }
}
