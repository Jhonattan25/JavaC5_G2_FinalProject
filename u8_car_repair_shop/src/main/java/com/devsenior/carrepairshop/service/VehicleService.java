package com.devsenior.carrepairshop.service;

import com.devsenior.carrepairshop.dto.VehicleRequest;
import com.devsenior.carrepairshop.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse create(VehicleRequest request);

    List<VehicleResponse> list();

    VehicleResponse findById(Long id);

    List<VehicleResponse> listByCustomer(Long customerId);

    void delete(Long id);
}
