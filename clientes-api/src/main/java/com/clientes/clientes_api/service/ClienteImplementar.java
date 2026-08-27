package com.clientes.clientes_api.service;

import com.clientes.clientes_api.dto.ClienteRequestDTO;
import com.clientes.clientes_api.dto.ClienteResponseDTO;
import com.clientes.clientes_api.exception.BadRequest;
import com.clientes.clientes_api.exception.ResourceNotFound;
import com.clientes.clientes_api.model.Cliente;
import com.clientes.clientes_api.model.Estado;
import com.clientes.clientes_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplementar implements ClienteService{
    private final ClienteRepository clienteRepository;

    public ClienteImplementar (ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDTO crearCliente (ClienteRequestDTO request){
        if (clienteRepository.countByCorreo(request.correo()) > 0){
            throw new BadRequest("El correo ya se encuentra registrado dentro de la base de datos. " + request.correo());
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());
        cliente.setCorreo(request.correo());
        cliente.setEstado(request.estado() != null ? request.estado() : Estado.ACTIVO);

        Cliente guardar = clienteRepository.save(cliente);
        return mapToDto(guardar);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorCorreo(String correo){
        Cliente cliente = clienteRepository.findByCorreo(correo).
                orElseThrow(() -> new ResourceNotFound("Cliente no fue encontrado con aquel correo: " + correo));
        return mapToDto(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerTodos(){
        return clienteRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional
    public ClienteResponseDTO actualizarCliente(String correo, ClienteRequestDTO request) {
        Cliente cliente = clienteRepository.findByCorreo(correo).
                orElseThrow(()-> new ResourceNotFound("Cliente no fue encontrado con aquel correo: " + correo));
        if(!cliente.getCorreo().equalsIgnoreCase(request.correo()) && clienteRepository.countByCorreo(request.correo())> 0){
            throw new BadRequest("El correo ya esta siendo utilizada por otro cliente en este momento: " + request.correo());
        }

        cliente.setNombre(request.nombre());
        cliente.setCorreo(request.correo());
        cliente.setEstado(request.estado() != null ? request.estado() : Estado.ACTIVO);

        Cliente actualizar = clienteRepository.save(cliente);
        return mapToDto(actualizar);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id){
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFound("No se pudo eliminar el cliente. Cliente no encontrado con la id: " + id);
        }
        clienteRepository.deleteById(id);
    }


    private ClienteResponseDTO mapToDto(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getEstado(),
                cliente.getFechaCreacion());
    }
}
