package mid.security.start.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mid.security.start.entities.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "aVeryLongSecretKeyThatIsAtLeast32BytesLong123";

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .claim("permissions",
                        user.getRole().getPermissions()
                                .stream()
                                .map(Enum::name)
                                .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getSignKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    public String extractRole(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    private SecretKey getSignKey() {
        byte[] bytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(bytes);
    }
}
