package com.example.golbackend.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public class AuthRequest {

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene el formato correcto")
    private String email;


    @NotBlank(message = "El password es requerido")
    private String password;



    public AuthRequest() {
        super();
    }


    public AuthRequest(String email, String password){
        this.email = email;
        this.password = password;
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
