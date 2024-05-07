package com.example.bookStore.service;


import com.example.bookStore.model.entities.Address;
import com.example.bookStore.model.entities.Shipment;
import com.example.bookStore.model.repo.AddressRepo;
import com.example.bookStore.model.repo.ShipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepo shipmentRepo;

    public void addShipment(Shipment shipment){
        shipmentRepo.save(shipment);
    }
}
