package com.devsenior.carrepairshop.dto;

public record CustomerResponse(
        Long id,
        String name,
        String documentNumber,
        String cell,
        String email,
        int vehicleCount
) {
}
