package com.example.golbackend.modules.user_managment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateRequest {


    private String userName;
    private String userLastName;

    @Email(message = "Please enter a valid email address")
    private String email;


    public UpdateRequest() {
    }

    public UpdateRequest(String userName, String userLastName, String email, boolean active) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


