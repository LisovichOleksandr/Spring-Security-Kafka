package mid.security.start.services;

import lombok.RequiredArgsConstructor;
import mid.security.start.controllers.dto.AuthResponseDto;
import mid.security.start.controllers.dto.LoginRequestDto;
import mid.security.start.controllers.dto.RefreshRequestDto;
import mid.security.start.entities.RefreshToken;
import mid.security.start.entities.User;
import mid.security.start.exceptions.LoginDeniedException;
import mid.security.start.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email()).orElseThrow();

        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new LoginDeniedException("Email or login is not valid");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto refresh(RefreshRequestDto requestDto) {
        RefreshToken refreshToken = refreshTokenService.findByToken(requestDto.refreshToken());

        if (refreshTokenService.isExpired(refreshToken)) {
            throw new RuntimeException("Refresh token expired.");
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());

        return new AuthResponseDto(accessToken, refreshToken.getToken());
    }
}
