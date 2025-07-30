package com.example.golbackend.modules.auth.controller;

import com.example.golbackend.modules.auth.dto.AuthRequest;
import com.example.golbackend.modules.auth.dto.AuthResponse;
import com.example.golbackend.modules.auth.dto.RegisterRequest;
import com.example.golbackend.modules.auth.security.JwtUtil;
import com.example.golbackend.modules.auth.services.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRegisterService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Lazy
    private com.example.golbackend.modules.auth.security.UserDetailsServiceImpl usuarioDetails;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = usuarioDetails.loadUserByUsername(authRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        usuarioService.registrarUsuario(registerRequest);
        return "Usuario registrado correctamente";
    }
}