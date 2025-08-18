package com.example.golbackend.modules.team_managment.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long id) {
        super("Team not found with id: " + id);
    }
}
