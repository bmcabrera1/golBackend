package com.example.golbackend.modules.auth.services;

import com.example.golbackend.modules.auth.dto.RegisterRequest;
import com.example.golbackend.modules.auth.model.Role;
import com.example.golbackend.modules.auth.model.User;
import com.example.golbackend.modules.auth.repositories.RoleRepository;
import com.example.golbackend.modules.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class UserRegisterService {

    @Autowired
    private UserRepository usuarioRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(RegisterRequest request) {
        Role rolUsuario = roleRepo.findByRoleName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role("ROLE_USER");
                    return roleRepo.save(newRole);
                });
        User nuevo = new User();
        nuevo.setUserName(request.getUserName());
        nuevo.setUserLastName(request.getUserLastName());
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setActive(true);
        nuevo.setRoles(Collections.singleton(rolUsuario));

        usuarioRepo.save(nuevo);
    }
}
