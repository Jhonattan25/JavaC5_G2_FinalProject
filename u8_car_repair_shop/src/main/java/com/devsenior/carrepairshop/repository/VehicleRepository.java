package com.devsenior.carrepairshop.repository;

import com.devsenior.carrepairshop.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByLicensePlate(String licensePlate);

    List<Vehicle> findByCustomerId(Long customerId);
}
