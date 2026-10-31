package software.ulpgc.loginservice.application.ports;

public interface SessionHandler {
    boolean checkUser(String username, String password);
}
