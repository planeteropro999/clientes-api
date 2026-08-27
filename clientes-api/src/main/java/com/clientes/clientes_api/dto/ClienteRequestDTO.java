package com.clientes.clientes_api.dto;

import jakarta.validation.constraints.Email;
import com.clientes.clientes_api.model.Estado;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El formato de correo no es válido")
        String correo,
        Estado estado
) {}