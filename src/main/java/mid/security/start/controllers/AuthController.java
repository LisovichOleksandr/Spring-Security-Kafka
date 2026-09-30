package mid.security.start.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mid.security.start.controllers.dto.AuthResponseDto;
import mid.security.start.controllers.dto.LoginRequestDto;
import mid.security.start.controllers.dto.RefreshRequestDto;
import mid.security.start.controllers.dto.RegisterRequestDto;
import mid.security.start.services.AuthService;
import mid.security.start.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDto payload) {
        userService.register(payload.email(), payload.password());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto payload) {
        AuthResponseDto authResponseDto = authService.login(payload);
        return ResponseEntity.ok(authResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@Valid @RequestBody RefreshRequestDto payload) {
        AuthResponseDto refresh = authService.refresh(payload);
        return ResponseEntity.ok(refresh);
    }
}
