package com.devsenior.carrepairshop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VehicleRequest(
        @NotBlank(message = "La placa es obligatoria")
        @Pattern(regexp = "[A-Z]{3}\\d{3}", message = "La placa debe tener formato ABC123")
        String licensePlate,

        @NotBlank(message = "La marca es obligatoria")
        String make,

        @NotBlank(message = "El modelo es obligatorio")
        String model,

        @NotNull(message = "El anio es obligatorio")
        @Min(value = 1950, message = "El anio no puede ser menor a 1950")
        @Max(value = 2030, message = "El anio no puede ser mayor a 2030")
        Integer year,

        @NotNull(message = "El cliente es obligatorio")
        Long customerId
) {
}
