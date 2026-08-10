package com.devsenior.carrepairshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ServiceOrderRequest(
        @NotBlank(message = "La descripcion es obligatoria")
        @Size(min = 10, max = 500, message = "La descripcion debe tener entre 10 y 500 caracteres")
        String description,

        @NotNull(message = "El costo es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a cero")
        BigDecimal cost,

        @NotNull(message = "El vehiculo es obligatorio")
        Long vehicleId
) {
}
