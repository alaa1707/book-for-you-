package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {


}
