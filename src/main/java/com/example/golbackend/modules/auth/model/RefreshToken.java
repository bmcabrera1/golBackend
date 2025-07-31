package com.example.golbackend.modules.auth.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name= "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "refresh_token_id")
    private Long id_refresh_token;

    @OneToOne
    @JoinColumn(name= "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expiryDate;


    public RefreshToken() {
    }

    public RefreshToken(Long id_refresh_token, User user, String token, LocalDateTime expiryDate) {
        this.id_refresh_token = id_refresh_token;
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Long getId_refresh_token() {
        return id_refresh_token;
    }

    public void setId_refresh_token(Long id_refresh_token) {
        this.id_refresh_token = id_refresh_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
