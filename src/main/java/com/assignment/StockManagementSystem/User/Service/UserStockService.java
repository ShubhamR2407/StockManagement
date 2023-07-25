package com.assignment.StockManagementSystem.User.Service;

import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import com.assignment.StockManagementSystem.Stock.Repository.StockRepository;
import com.assignment.StockManagementSystem.User.Repository.Modals.UserStock;
import com.assignment.StockManagementSystem.User.Repository.UserRepository;
import com.assignment.StockManagementSystem.User.Repository.UserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserStockService {
    @Autowired
    UserStockRepository userStockRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockRepository stockRepository;


    public Stock buyStock(Long userId, Long stockId) {
        UserStock userStock = new UserStock();

        userStock.setUserId(userId);
        userStock.setStockId(stockId);
        userStock.setBuyDate(new Date());

        userStockRepository.save(userStock);
        return stockRepository.findByStockId(stockId);
    }

    public void sellStock(Long userId, Long stockId) {
        System.out.println(userId + " " + stockId);
        UserStock  userStock = userStockRepository.findByStockIdAndUserId(stockId,userId);
        System.out.println("here " + userStock);
        userStockRepository.delete(userStock);
        return ;
    }

    public List<Stock> getAllStockByUserId(Long userId) {
        List<Long> list = userStockRepository.findAllStockIdsByUserId(userId);

        List<Stock> stockList = new ArrayList<>();
        list.forEach((stockId) -> {
            stockList.add(stockRepository.findByStockId(stockId));
        });
        return stockList;
    }


}
