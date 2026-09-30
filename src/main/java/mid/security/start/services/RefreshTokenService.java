package mid.security.start.services;

import lombok.RequiredArgsConstructor;
import mid.security.start.entities.RefreshToken;
import mid.security.start.entities.User;
import mid.security.start.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiresAt(Instant.now().plus(30, ChronoUnit.DAYS))
                .build();

        repository.save(refreshToken);

        return refreshToken.getToken();
    }

    public RefreshToken findByToken(String token) {
        return repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found."));
    }

    public boolean isExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiresAt().isBefore(Instant.now());
    }
}
