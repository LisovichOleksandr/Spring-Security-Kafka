package mid.security.start.services;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mid.security.start.entities.User;

public interface UserService {
    public User register(String email, String password);

    String login(String email, String password);
}
