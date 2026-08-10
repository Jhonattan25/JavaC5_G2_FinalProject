package com.devsenior.carrepairshop.service;

import com.devsenior.carrepairshop.dto.CustomerRequest;
import com.devsenior.carrepairshop.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    List<CustomerResponse> list();

    CustomerResponse findById(Long id);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
