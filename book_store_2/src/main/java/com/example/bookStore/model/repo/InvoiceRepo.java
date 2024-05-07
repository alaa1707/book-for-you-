package com.example.bookStore.model.repo;

import com.example.bookStore.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
}
