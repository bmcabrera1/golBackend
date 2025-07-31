package com.example.golbackend.modules.user_managment.services;

import com.example.golbackend.modules.auth.model.User;
import com.example.golbackend.modules.auth.repositories.RefreshTokenRepository;
import com.example.golbackend.modules.auth.repositories.UserRepository;
import com.example.golbackend.modules.auth.services.RefreshTokenService;
import com.example.golbackend.modules.user_managment.dto.ChangePasswordRequest;
import com.example.golbackend.modules.user_managment.dto.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenService refreshTokenService;


    public void updateUser(String email, UpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }

        if (request.getUserLastName() != null) {
            user.setUserLastName(request.getUserLastName());
        }

        userRepository.save(user);
    }
    public void updatePassword(String email, ChangePasswordRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Error: The current password does not match the one provided");
        } else {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        }

    }


    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isActive()) {
            throw new RuntimeException("User not found");
        }

        refreshTokenService.deleteByUser(user.getUserId());

        user.setActive(false);

        userRepository.save(user);

    }

}
