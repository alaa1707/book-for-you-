package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Cart;
import com.example.bookStore.model.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    @Query("select cart from Cart cart where cart.user.id= :user_id ")
    Optional<Cart> findByUserId(@Param("user_id") int user_id);
}
