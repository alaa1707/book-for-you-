package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem ,Integer> {
    List<OrderItem> findByOrderId(int id);
}
