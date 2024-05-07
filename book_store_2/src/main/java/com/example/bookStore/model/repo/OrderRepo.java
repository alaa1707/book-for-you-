package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {
//    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
//    List<Order> getCustomerOrders(@Param("userId") int userId);}
    List<Order> findByUserId(int user_id);
}
