package mid.security.start.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @ManyToOne
    private User user;
}
