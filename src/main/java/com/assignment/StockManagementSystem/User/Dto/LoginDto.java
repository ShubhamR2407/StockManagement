package com.assignment.StockManagementSystem.User.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDto {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 10, message ="Username must be minimum of 3 characters")
    private String name;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 to 20 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
            message = "Password must contain at least one special character, one digit, one uppercase letter, and one lowercase letter.")
    private String password;

    public LoginDto(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public LoginDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
