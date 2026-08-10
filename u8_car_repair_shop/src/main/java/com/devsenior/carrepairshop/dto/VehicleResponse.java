package com.devsenior.carrepairshop.dto;

public record VehicleResponse(
        Long id,
        String licensePlate,
        String make,
        String model,
        Integer year,
        String customerName
) {
}
