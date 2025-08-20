package com.example.golbackend.modules.auth.controller;

import com.example.golbackend.modules.auth.dto.*;
import com.example.golbackend.modules.auth.exception.TokenRefreshException;
import com.example.golbackend.modules.auth.model.RefreshToken;
import com.example.golbackend.modules.auth.model.User;
import com.example.golbackend.modules.auth.security.JwtUtil;
import com.example.golbackend.modules.auth.services.RefreshTokenService;
import com.example.golbackend.modules.auth.services.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String jwt = jwtUtil.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUserId());

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        AuthResponse response = new AuthResponse(
                jwt,
                refreshToken.getToken(),
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                roles
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new TokenRefreshException(
                        requestRefreshToken, "Refresh token is not in database!")
                );

        refreshTokenService.verifyExpirationDate(refreshToken);

        User user = refreshToken.getUser();
        String newJwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new TokenRefreshResponse(newJwt, requestRefreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refreshTokenService.deleteByUser(user.getUserId());
        return ResponseEntity.ok("Log out successful!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userRegisterService.registrarUsuario(registerRequest);
        return ResponseEntity.status(201).body("User created successfully!");
    }
}
