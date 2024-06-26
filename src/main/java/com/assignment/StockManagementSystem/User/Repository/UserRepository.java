package com.assignment.StockManagementSystem.User.Repository;

import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.id = ?1 OR u.name = ?2")
    User findByIdOrName(Long id, String name);

    boolean existsByName(String name);
    boolean existsByAadharNumber(Long aadharNumber);
    boolean existsByEmail(String email);
}
