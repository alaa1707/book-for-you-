package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Integer> {
    Optional<CartItem> findByCartIdAndBookId(int cartId, long bookId);
    List<CartItem> findByCartId(int cartId);

}
