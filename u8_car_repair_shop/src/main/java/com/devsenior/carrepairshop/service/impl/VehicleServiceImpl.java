package com.devsenior.carrepairshop.service.impl;

import com.devsenior.carrepairshop.dto.VehicleRequest;
import com.devsenior.carrepairshop.dto.VehicleResponse;
import com.devsenior.carrepairshop.exception.DuplicateException;
import com.devsenior.carrepairshop.exception.NotFoundException;
import com.devsenior.carrepairshop.model.Customer;
import com.devsenior.carrepairshop.model.Vehicle;
import com.devsenior.carrepairshop.repository.CustomerRepository;
import com.devsenior.carrepairshop.repository.VehicleRepository;
import com.devsenior.carrepairshop.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public VehicleResponse create(VehicleRequest request) {
        if (vehicleRepository.existsByLicensePlate(request.licensePlate())) {
            throw new DuplicateException(
                    "Ya existe un vehiculo con la placa " + request.licensePlate());
        }

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException(
                        "No existe un cliente con id " + request.customerId()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.licensePlate());
        vehicle.setMake(request.make());
        vehicle.setModel(request.model());
        vehicle.setYear(request.year());
        vehicle.setCustomer(customer);

        return toResponse(vehicleRepository.save(vehicle));
    }

    @Override
    public List<VehicleResponse> list() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public VehicleResponse findById(Long id) {
        return toResponse(findVehicle(id));
    }

    @Override
    public List<VehicleResponse> listByCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new NotFoundException(
                    "No existe un cliente con id " + customerId);
        }
        return vehicleRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        vehicleRepository.delete(findVehicle(id));
    }

    private Vehicle findVehicle(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "No existe un vehiculo con id " + id));
    }

    private VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getLicensePlate(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getCustomer().getName()
        );
    }
}
