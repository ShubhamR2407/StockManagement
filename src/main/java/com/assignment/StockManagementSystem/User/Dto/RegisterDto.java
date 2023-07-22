package com.assignment.StockManagementSystem.User.Dto;

import jakarta.validation.constraints.*;

public class RegisterDto {
    private Long userId;

    @NotBlank
    @Size(min = 3, max = 10, message ="Username must be minimum of 3 characters")
    private String name;

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotNull
    private Long number;

    @NotNull
    private Long aadharNumber;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 to 20 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
            message = "Password must contain at least one special character, one digit, one uppercase letter, and one lowercase letter.")
    private String password;

    public RegisterDto(String name, String email, Long number, Long aadharNumber, String password) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.aadharNumber = aadharNumber;
        this.password = password;
    }

    public RegisterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(Long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
