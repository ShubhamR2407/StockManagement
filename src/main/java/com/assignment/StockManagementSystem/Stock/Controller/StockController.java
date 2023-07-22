package com.assignment.StockManagementSystem.Stock.Controller;

import com.assignment.StockManagementSystem.Stock.Dto.StockDto;
import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import com.assignment.StockManagementSystem.Stock.Dto.Service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/save")
    public ResponseEntity<Stock> addStock(@Valid @RequestBody StockDto stockDto) {
        try {
            Stock createdStock = stockService.addStock(stockDto);
            return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id,@Valid @RequestBody StockDto stockDto) {
        try {
            Stock updatedStock = stockService.updateStock(id, stockDto);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        try {
            ResponseEntity<String> response = stockService.deleteStock(id);
            return response;
        }catch (Exception e) {
            return new ResponseEntity<>("Error Deleting Stock", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        try {
            List<Stock> stockList = stockService.getAllStocks();
            if(stockList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stockList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
