package software.ulpgc.loginservice.infrastructure.adapters;

import software.ulpgc.loginservice.application.domain.model.User;
import software.ulpgc.loginservice.application.ports.UserSerializer;

public class JsonUserSerializer implements UserSerializer {
    @Override
    public String serialize(User user){
        return "";
    }
}
