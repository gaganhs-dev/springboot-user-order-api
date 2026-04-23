package com.user_order_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Email is required")
    @Email(message = "please provide a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
