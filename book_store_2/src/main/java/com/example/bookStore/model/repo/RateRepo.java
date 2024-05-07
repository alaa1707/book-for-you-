package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.model.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate,Integer> {
}
