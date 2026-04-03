package abdumalik.prodev.securitymodule.service;

import abdumalik.prodev.securitymodule.config.JwtUtil;
import abdumalik.prodev.securitymodule.dto.AuthRequest;
import abdumalik.prodev.securitymodule.dto.AuthResponse;
import abdumalik.prodev.securitymodule.model.User;
import abdumalik.prodev.securitymodule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String token = jwtUtil.generateToken(userDetails);

        log.info("User {} authenticated successfully", request.email());

        return new AuthResponse(token);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        log.info("User {} registered successfully", savedUser.getEmail());
        return savedUser;
    }

}
