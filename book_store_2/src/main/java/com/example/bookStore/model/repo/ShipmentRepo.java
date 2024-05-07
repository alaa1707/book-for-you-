package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepo extends JpaRepository<Shipment,Integer> {
}
