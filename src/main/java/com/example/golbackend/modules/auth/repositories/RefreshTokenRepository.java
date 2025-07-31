package com.example.golbackend.modules.auth.repositories;

import com.example.golbackend.modules.auth.model.RefreshToken;
import com.example.golbackend.modules.auth.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Transactional
    @Modifying
    int deleteByUser(User user);
}
