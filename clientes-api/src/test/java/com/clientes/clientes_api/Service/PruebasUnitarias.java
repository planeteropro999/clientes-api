package com.clientes.clientes_api.service;

import com.clientes.clientes_api.dto.ClienteRequestDTO;
import com.clientes.clientes_api.dto.ClienteResponseDTO;
import com.clientes.clientes_api.exception.BadRequest;
import com.clientes.clientes_api.exception.ResourceNotFound;
import com.clientes.clientes_api.model.Cliente;
import com.clientes.clientes_api.model.Estado;
import com.clientes.clientes_api.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteImplementar clienteService;

    private Cliente cliente;
    private ClienteRequestDTO clienteRequestDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1L, "Sebastian Mendoza", "sebastian@correo.com", Estado.ACTIVO, LocalDateTime.now());
        clienteRequestDTO = new ClienteRequestDTO("Sebastian Mendoza", "sebastian@correo.com", Estado.ACTIVO);
    }

    @Test
    void crearCliente_Exitoso() {
        when(clienteRepository.countByCorreo(anyString())).thenReturn(0L);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDTO resultado = clienteService.crearCliente(clienteRequestDTO);

        assertNotNull(resultado);
        assertEquals("Sebastian Mendoza", resultado.nombre());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void crearCliente_LanzaExcepcion_CuandoCorreoExiste() {
        when(clienteRepository.countByCorreo(anyString())).thenReturn(1L);

        assertThrows(BadRequest.class, () -> clienteService.crearCliente(clienteRequestDTO));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void obtenerPorCorreo_LanzaExcepcion_CuandoClienteNoExiste() {
        assertThrows(ResourceNotFound.class, () -> clienteService.obtenerPorCorreo(clienteRequestDTO.correo()));
    }
}