package com.clientes.clientes_api.Controller;

import com.clientes.clientes_api.dto.ClienteRequestDTO;
import com.clientes.clientes_api.dto.ClienteResponseDTO;
import com.clientes.clientes_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> clearCliente(@Valid @RequestBody ClienteRequestDTO request){
        ClienteResponseDTO nuevocliente = clienteService.crearCliente(request);
        return new ResponseEntity<>(nuevocliente, HttpStatus.CREATED);
    }

    @GetMapping("/{correo}")
    public ResponseEntity<ClienteResponseDTO> obtenerPorCorreo(@PathVariable String correo){
        return ResponseEntity.ok(clienteService.obtenerPorCorreo(correo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @PutMapping("/{correo}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable String correo, @Valid @RequestBody ClienteRequestDTO request){
        return ResponseEntity.ok(clienteService.actualizarCliente(correo, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>  eliminarCliente(Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
