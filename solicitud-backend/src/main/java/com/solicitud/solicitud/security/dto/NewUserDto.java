package com.solicitud.solicitud.security.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;


public class NewUserDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String position;
    private String password;
    @NotBlank
    private Set<String> roles;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRole(Set<String> roles) {
        this.roles = roles;
    }

}
