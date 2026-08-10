package com.devsenior.carrepairshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String name,

        @NotBlank(message = "La cedula es obligatoria")
        @Pattern(regexp = "\\d{6,15}", message = "La cedula debe contener entre 6 y 15 digitos")
        String documentNumber,

        @NotBlank(message = "El telefono es obligatorio")
        @Pattern(regexp = "\\d{7,15}", message = "El telefono debe contener entre 7 y 15 digitos")
        String cell,

        @Email(message = "El correo no tiene un formato valido")
        String email
) {
}
