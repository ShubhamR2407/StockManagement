package com.assignment.StockManagementSystem.User.Repository;

import com.assignment.StockManagementSystem.User.Repository.Modals.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Long> {
    @Query("SELECT us.stockId FROM UserStock us WHERE us.userId = :userId")
    List<Long> findAllStockIdsByUserId(Long userId);

    UserStock findByStockIdAndUserId(Long stockId, Long userId);
}
