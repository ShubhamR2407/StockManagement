package com.assignment.StockManagementSystem.Stock.Repository.Modals;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long stockId;

    @Column
    private String name;

    @Column
    private String symbol;

    @Column
    private String type;

    @Column
    private Double currentPrice;

    @Column
    private Date expDate;

    public Stock(String name, String symbol, String type, Double currentPrice, Date expDate) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.currentPrice = currentPrice;
        this.expDate = expDate;
    }

    public Stock() {
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
