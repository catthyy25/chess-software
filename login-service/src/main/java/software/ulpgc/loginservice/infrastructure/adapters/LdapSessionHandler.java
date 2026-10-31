package software.ulpgc.loginservice.infrastructure.adapters;

import software.ulpgc.loginservice.application.ports.SessionHandler;

public class LdapSessionHandler implements SessionHandler {
    @Override
    public boolean checkUser(String username, String password){
        return false;
    }
}
