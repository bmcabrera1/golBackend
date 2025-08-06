package com.example.golbackend.modules.auth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlredyExists extends RuntimeException {
    public UserAlredyExists(String message) {
        super(message);
    }
}
