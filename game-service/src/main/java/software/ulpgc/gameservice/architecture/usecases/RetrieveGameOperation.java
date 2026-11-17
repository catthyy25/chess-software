package software.ulpgc.gameservice.architecture.usecases;

import software.ulpgc.gameservice.architecture.UseCase;
import software.ulpgc.gameservice.architecture.ports.GameStore;

public class RetrieveGameOperation implements UseCase {
    private final GameStore store;

    public RetrieveGameOperation(GameStore store) {
        this.store = store;
    }

    @Override
    public Output execute(Input input) {
        return new Output() {
            @Override
            public int status() {
                return 200;
            }

            @Override
            public byte[] result() {
                return store.load(input.get("id")).getBytes();
            }
        };
    }
}
