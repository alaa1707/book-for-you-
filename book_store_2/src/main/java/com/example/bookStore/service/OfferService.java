package com.example.bookStore.service;


import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.model.repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepo offerRepo;




    public List<Offer> getAllOffers(){
      return offerRepo.findAll();
    }

    public void deleteOffer(int id){
        offerRepo.deleteById(id);
    }

    public void saveOffer(Offer offer){
        offerRepo.save(offer);
    }


    public Optional<Offer> getOffer(int id){
        return offerRepo.findById(id);
    }
}
