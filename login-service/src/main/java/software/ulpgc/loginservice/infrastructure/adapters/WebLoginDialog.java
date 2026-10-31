package software.ulpgc.loginservice.infrastructure.adapters;

import software.ulpgc.loginservice.application.ports.LoginDialog;

public class WebLoginDialog implements LoginDialog {
    @Override
    public String username() {
        return "";
    }

    @Override
    public String password() {
        return "";
    }
}
