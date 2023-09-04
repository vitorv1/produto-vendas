package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import com.example.produtovendas.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository repository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testeMetodoCadastroCliente() throws Exception {

        List<ClienteEntity> clienteEntitie = new ArrayList<>();
        when(repository.findAll()).thenReturn(clienteEntitie);
        when(repository.save(any())).thenReturn(new ClienteEntity());

        String clienteJson = "{\"nome\": \"Mariana\", \"cpf\":\"456357159-17\", \"email\": \"email@gmail.com\", \"numeroTelefone\": \"(44)99874-8356\"}";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson));


        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Mariana"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("456357159-17"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@gmail.com"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefone").value("(44)99874-8356"));
    }

    @Test
    void concultarTodosClientes() {
    }

    @Test
    void concultaClientePorId() {
    }

    @Test
    void deletarCliente() {
    }

    @Test
    void altararCliente() {
    }
}