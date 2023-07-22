package com.assignment.StockManagementSystem.Stock.Dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;

import java.util.Date;

public class StockDto {
    private Long stockId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 10)
    private String symbol;

    @NotBlank
    @Size(min = 1, max = 50)
    private String type;

    @NotNull
    @Min(value = 0, message = "Current price cannot be negative")
    private Double currentPrice;

    @NotNull
    @Future(message = "Expiration date must be in the future")
    @Temporal(TemporalType.DATE)
    private Date expDate;

    public StockDto(String name, String symbol, String type, Double currentPrice, Date expDate) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.currentPrice = currentPrice;
        this.expDate = expDate;
    }

    public StockDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", currentPrice=" + currentPrice +
                ", expDate='" + expDate + '\'' +
                '}';
    }
}
