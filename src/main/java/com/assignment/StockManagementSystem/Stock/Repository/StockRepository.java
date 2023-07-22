package com.assignment.StockManagementSystem.Stock.Repository;

import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
