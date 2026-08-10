package com.devsenior.carrepairshop.repository;

import com.devsenior.carrepairshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByDocumentNumber(String documentNumber);
}