package com.assignment.StockManagementSystem.User.Repository.Modals;

import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class UserStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long userId;
    private Long stockId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date buyDate;

    public UserStock(long id, Long userId, Long stockId, Date buyDate) {
        this.id = id;
        this.userId = userId;
        this.stockId = stockId;
        this.buyDate = buyDate;
    }

    public UserStock() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "UserStock{" +
                "id=" + id +
                ", userId=" + userId +
                ", stockId=" + stockId +
                ", buyDate=" + buyDate +
                '}';
    }
}
