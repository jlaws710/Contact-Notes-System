package com.contact_notes.service;

import com.contact_notes.config.JwtUtil;
import com.contact_notes.dto.AuthRequest;
import com.contact_notes.dto.AuthResponse;
import com.contact_notes.model.Users;
import com.contact_notes.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authManager, UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(AuthRequest request) {

        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(request.getUsername());

            return new AuthResponse(token);
        } catch (RuntimeException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    public void register(AuthRequest request) {
        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(encoder.encode(request.getPassword()));
        users.setRole("USER");
        userRepo.save(users);
    }
}
