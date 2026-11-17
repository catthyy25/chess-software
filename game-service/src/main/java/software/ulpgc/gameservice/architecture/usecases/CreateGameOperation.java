package software.ulpgc.gameservice.architecture.usecases;

import software.ulpgc.gameservice.architecture.UseCase;
import software.ulpgc.gameservice.architecture.ports.GameStore;

import java.util.UUID;

public class CreateGameOperation implements UseCase {
    private final GameStore store;

    public CreateGameOperation(GameStore store) {
        this.store = store;
    }

    @Override
    public Output execute(Input input) {
        String id = UUID.randomUUID().toString();
        store.save(id, "");
        return new Output() {
            @Override
            public int status() {
                return 200;
            }

            @Override
            public byte[] result() {
                return id.getBytes();
            }
        };
    }
}