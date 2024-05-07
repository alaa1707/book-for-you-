package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> {
}
