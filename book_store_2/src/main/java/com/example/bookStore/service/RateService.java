package com.example.bookStore.service;


import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.model.entities.Rate;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.model.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    @Autowired
    private RateRepo rateRepo;

    public List<Rate> getAllRates(){
        return rateRepo.findAll();
    }

    public void addUserRate(User user, Book book, Rate rate){
        boolean isExistOldRate=false;
        for (Rate rate1: book.getRates()){
            if(rate1.getUser().getId()== user.getId()) {
                isExistOldRate = true;
                rate.setId(rate1.getId());
                break;
            }
        }
            rate.setBook(book);
            rate.setUser(user);
        rateRepo.save(rate);

    }

}
