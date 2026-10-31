package software.ulpgc.loginservice.application.ports;

import software.ulpgc.loginservice.application.domain.model.User;

public interface UserSerializer {
    String serialize(User user);
}
