package com.example.bookStore.service;


import com.example.bookStore.model.entities.Address;
import com.example.bookStore.model.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public void addAddress(Address address){
        addressRepo.save(address);
    }

}
