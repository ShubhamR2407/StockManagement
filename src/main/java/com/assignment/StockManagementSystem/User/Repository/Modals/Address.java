package com.assignment.StockManagementSystem.User.Repository.Modals;

import jakarta.persistence.*;

@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private int pincode;

    public Address(String state, String city, int pincode) {
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }

    public Address() {
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
        return "Address{" +
                "addressId=" + addressId +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
