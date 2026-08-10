package com.devsenior.carrepairshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ServiceOrderResponse(
        Long id,
        String description,
        LocalDate entryDate,
        LocalDate deliveryDate,
        String status,
        BigDecimal cost,
        String licensePlate,
        String customerName
) {
}
