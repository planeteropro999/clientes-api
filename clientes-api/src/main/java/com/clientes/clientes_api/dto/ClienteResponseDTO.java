package com.clientes.clientes_api.dto;

import com.clientes.clientes_api.model.Estado;
import java.time.LocalDateTime;

public record ClienteResponseDTO(Long id,
                                 String nombre,
                                 String correo,
                                 Estado estado,
                                 LocalDateTime fechaCreacion) {
}