package software.ulpgc.gameservice.infrastructure;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import software.ulpgc.gameservice.architecture.UseCase;
import software.ulpgc.gameservice.architecture.usecases.*;
import software.ulpgc.gameservice.architecture.usecases.CreateGameOperation;
import software.ulpgc.gameservice.architecture.usecases.RetrieveGameOperation;
import software.ulpgc.gameservice.architecture.usecases.UpdateGameOperation;
import software.ulpgc.gameservice.infrastructure.adapters.JavalinContextInput;
import software.ulpgc.gameservice.infrastructure.adapters.persistance.FileGameStore;

import java.io.File;

public class Main {

    static void main() {
        Javalin.create(Main::init).start(8080);
    }

    private static void init(JavalinConfig config) {
        config.routes.get("/", c->execute(c, new RetrieveGameOperation(new FileGameStore(new File("games")))));
        config.routes.put("/", c->execute(c, new UpdateGameOperation()));
        config.routes.post("/", c->execute(c, new CreateGameOperation(new FileGameStore(new File("games")))));
    }

    private static void execute(Context context, UseCase usecase) {
        UseCase.Output output = usecase.execute(new JavalinContextInput(context));
        context.result(output.result());
        context.status(output.status());
    }

}
