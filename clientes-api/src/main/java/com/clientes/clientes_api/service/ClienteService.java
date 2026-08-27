package com.clientes.clientes_api.service;

import com.clientes.clientes_api.dto.ClienteRequestDTO;
import com.clientes.clientes_api.dto.ClienteResponseDTO;
import com.clientes.clientes_api.model.Estado;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO crearCliente(ClienteRequestDTO request);
    ClienteResponseDTO obtenerPorCorreo(String correo);
    List<ClienteResponseDTO> obtenerTodos();
    ClienteResponseDTO actualizarCliente(String correo, ClienteRequestDTO request);
    void eliminarCliente(Long id);
}