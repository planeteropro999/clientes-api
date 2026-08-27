package com.clientes.clientes_api.Controlador;

import com.clientes.clientes_api.dto.ClienteRequestDTO;
import com.clientes.clientes_api.model.Estado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerIantegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCliente_Retorna201Created() throws Exception {
        ClienteRequestDTO request = new ClienteRequestDTO("Sebastian Mendoza", "sebas.mendoza@gmail.com", Estado.ACTIVO);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Sebastian Mendoza"))
                .andExpect(jsonPath("$.correo").value("sebas.mendoza@gmail.com"));
    }

    @Test
    void crearCliente_Retorna400BadRequest_CuandoCorreoEsInvalido() throws Exception {
        ClienteRequestDTO request = new ClienteRequestDTO("Sebastian Mendoza", "correo-invalido", Estado.ACTIVO);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Error de Validación"));
    }
}