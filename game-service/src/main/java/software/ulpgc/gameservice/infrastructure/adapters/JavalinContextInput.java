package software.ulpgc.gameservice.infrastructure.adapters;

import io.javalin.http.Context;
import software.ulpgc.gameservice.architecture.UseCase;

public class JavalinContextInput implements UseCase.Input {
    private final Context context;

    public JavalinContextInput(Context context) {
        this.context = context;
    }

    @Override
    public String get(String key) {
        return context.pathParam(key);
    }

}
