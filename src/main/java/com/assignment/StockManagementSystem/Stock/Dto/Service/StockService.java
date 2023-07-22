package com.assignment.StockManagementSystem.Stock.Dto.Service;

import com.assignment.StockManagementSystem.Stock.Dto.StockDto;
import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import com.assignment.StockManagementSystem.Stock.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StockService {
    @Autowired
    StockRepository stockRepository;

    public Stock addStock(StockDto stockDto) {
        Stock stock = new Stock();

        stock.setName(stockDto.getName());
        stock.setSymbol(stockDto.getSymbol());
        stock.setType(stockDto.getType());
        stock.setCurrentPrice(stockDto.getCurrentPrice());
        stock.setExpDate(stockDto.getExpDate());
        stockRepository.save(stock);
        return stock;
    }

    public Stock updateStock(Long id, StockDto stockDto) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();

            stock.setCurrentPrice(stockDto.getCurrentPrice());
            stock.setType(stockDto.getType());
            stock.setExpDate(stockDto.getExpDate());

            return stockRepository.save(stock);
        }

        return null;
    }

    public ResponseEntity<String> deleteStock(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if(stockOptional.isPresent()) {
            Stock stock = stockOptional.get();

            stockRepository.delete(stock);
            return new ResponseEntity<>("Stock Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Stock not Found",HttpStatus.NOT_FOUND);
    }

    public List<Stock> getAllStocks() {
        List<Stock> stockList = stockRepository.findAll();
        return stockList;
    }
}
