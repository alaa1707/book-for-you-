package com.example.bookStore.service;


import com.example.bookStore.model.entities.Category;
import com.example.bookStore.model.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategoriesNames() {
        List<Category> categories = categoryRepo.findAll();

//        List<String> names=  new ArrayList<>();
//
//        for (Category category: categories){
//            names.add(
//                    category.getName()
//            );
//        }
//
//        return names;
//    }
        return categories;

    }

    public Optional<Category> getCategory(int id){
      return   categoryRepo.findById(id);
    }
}
