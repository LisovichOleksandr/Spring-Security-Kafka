package mid.security.start.controllers.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}
