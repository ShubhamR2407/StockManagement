package com.assignment.StockManagementSystem.User.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDto {
    private Long addressId;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotNull
    @Digits(integer = 6, fraction = 0, message = "Pincode must have 6 digits")
    private int pincode;

    public AddressDto(String state, String city, int pincode) {
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }

    public AddressDto() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "addressId=" + addressId +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
