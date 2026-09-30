package mid.security.start.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mid.security.start.entities.Role;
import mid.security.start.entities.User;
import mid.security.start.exceptions.LoginDeniedException;
import mid.security.start.exceptions.UserExistsException;
import mid.security.start.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    @Override
    public User register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new UserExistsException("User already exist with this email");
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();
        return userRepository.save(user);

    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginDeniedException("Email or login is not valid"));


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginDeniedException("Email or login is not valid");
        }
        return jwtService.generateToken(user);
    }
}
