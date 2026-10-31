package software.ulpgc.loginservice.application.usecases;

import software.ulpgc.loginservice.application.UseCase;
import software.ulpgc.loginservice.application.ports.ErrorDialog;
import software.ulpgc.loginservice.application.ports.LoginDialog;
import software.ulpgc.loginservice.application.ports.SessionHandler;

public class LoginUseCase implements UseCase {
    private final LoginDialog input;
    private final SessionHandler sessionHandler;
    private final ErrorDialog output;

    public LoginUseCase(LoginDialog input, SessionHandler sessionHandler, ErrorDialog output) {
        this.input = input;
        this.sessionHandler = sessionHandler;
        this.output = output;
    }

    @Override
    public void execute(){
        if (sessionHandler.checkUser(input.username(), input.password())){
            output.show("El usuario existe.");
        } else {
            output.show("El usuario no existe.");
        }
    }
}
