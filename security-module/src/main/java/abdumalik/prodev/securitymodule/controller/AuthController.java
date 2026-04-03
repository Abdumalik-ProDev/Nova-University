package abdumalik.prodev.securitymodule.controller;

import abdumalik.prodev.securitymodule.dto.AuthRequest;
import abdumalik.prodev.securitymodule.dto.AuthResponse;
import abdumalik.prodev.securitymodule.model.User;
import abdumalik.prodev.securitymodule.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticate user with email and password")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Register a new user")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User savedUser = authService.register(user);
        return ResponseEntity.ok(savedUser);
    }
}
