package com.devsenior.carrepairshop.service.impl;

import com.devsenior.carrepairshop.dto.CustomerRequest;
import com.devsenior.carrepairshop.dto.CustomerResponse;
import com.devsenior.carrepairshop.exception.DuplicateException;
import com.devsenior.carrepairshop.exception.NotFoundException;
import com.devsenior.carrepairshop.model.Customer;
import com.devsenior.carrepairshop.repository.CustomerRepository;
import com.devsenior.carrepairshop.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        if (customerRepository.existsByDocumentNumber(request.documentNumber())) {
            throw new DuplicateException(
                    "Ya existe un cliente con la cedula " + request.documentNumber());
        }
        Customer customer = toEntity(request);
        return toResponse(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> list() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse findById(Long id) {
        return toResponse(findCustomer(id));
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = findCustomer(id);

        if (!customer.getDocumentNumber().equals(request.documentNumber())
                && customerRepository.existsByDocumentNumber(request.documentNumber())) {
            throw new DuplicateException(
                    "Ya existe un cliente con la cedula " + request.documentNumber());
        }

        customer.setName(request.name());
        customer.setDocumentNumber(request.documentNumber());
        customer.setCell(request.cell());
        customer.setEmail(request.email());

        return toResponse(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = findCustomer(id);
        customerRepository.delete(customer);
    }

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "No existe un cliente con id " + id));
    }

    private Customer toEntity(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setDocumentNumber(request.documentNumber());
        customer.setCell(request.cell());
        customer.setEmail(request.email());
        return customer;
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getDocumentNumber(),
                customer.getCell(),
                customer.getEmail(),
                customer.getVehicles().size()
        );
    }
}
