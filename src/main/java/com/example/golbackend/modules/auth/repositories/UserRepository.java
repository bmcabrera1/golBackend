package com.example.golbackend.modules.auth.repositories;

import java.util.Optional;

import com.example.golbackend.modules.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail(String email);


}
