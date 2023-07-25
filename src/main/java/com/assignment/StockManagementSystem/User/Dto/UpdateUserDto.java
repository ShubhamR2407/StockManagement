package com.assignment.StockManagementSystem.User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UpdateUserDto {
    private Long id;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    private Long number;
    @NotNull
    private Long aadharNumber;

    public UpdateUserDto(Long id, String email, Long number, Long aadharNumber) {
        this.id = id;
        this.email = email;
        this.number = number;
        this.aadharNumber = aadharNumber;
    }

    public UpdateUserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UpdateUserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", aadharNumber=" + aadharNumber +
                '}';
    }
}
