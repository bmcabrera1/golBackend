package com.example.golbackend.modules.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.golbackend.modules.auth.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository usuarioRepository;

    public UserDetailsServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found email: " + email));
    }

}


