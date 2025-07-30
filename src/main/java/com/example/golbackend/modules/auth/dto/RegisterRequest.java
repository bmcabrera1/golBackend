package com.example.golbackend.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {


    @NotBlank(message = "El nombre es obligatorio")
    private String userName;

    @NotBlank(message = "El apellido es obligatorio")
    private String userLastName;

    @Email(message = "El email es obligatorio")
    private String email;

    @Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    private String password;


    public RegisterRequest() {
        super();
    }


    public RegisterRequest(String userName, String userLastName, String email, String password) {
        super();
        this.userName = userName;
        this.userLastName = userLastName;
        this.email = email;
        this.password = password;
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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}



