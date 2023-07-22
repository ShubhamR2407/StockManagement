package com.assignment.StockManagementSystem.User.Repository.Modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Long number;

    @Column
    private Long aadharNumber;

    @Column
    private String password;

    @Column
    private Long stocks;

    @Column
    private boolean isAdmin = false;

    @Column
    private boolean isCompany = false;

    public User(String name, String email, Long number, Long aadharNumber, String password) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.aadharNumber = aadharNumber;
        this.password = password;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }

    @JsonInclude
    public boolean isAdmin() {
        return isAdmin;
    }

    @JsonInclude
    public boolean isCompany() {
        return isCompany;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", aadharNumber=" + aadharNumber +
                ", password='" + password + '\'' +
                ", stocks=" + stocks +
                ", isAdmin=" + isAdmin +
                ", isCompany=" + isCompany +
                '}';
    }
}
