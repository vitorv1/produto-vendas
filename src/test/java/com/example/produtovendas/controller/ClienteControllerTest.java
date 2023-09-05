package com.example.produtovendas.controller;

import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository repository;


    @Test
    void testeMetodoCadastroCliente() throws Exception {

        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(builderCliente().get(0));

        String clienteJson = "{\"nome\": \"Mariana\", \"cpf\":\"456357159-17\", \"email\": \"email@gmail.com\", \"numeroTelefone\": \"(44)99874-8356\"}";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson));

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(builderCliente().get(0).getNome()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(builderCliente().get(0).getCpf()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(builderCliente().get(0).getEmail()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefone").value(builderCliente().get(0).getNumeroTelefone()));
    }

    @Test
    void testeMetodoConsultarTodosClientes() throws Exception {
        when(repository.findAll()).thenReturn(builderCliente());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(builderCliente().get(0).getId())))
                .andExpect(jsonPath("$[0].nome", is(builderCliente().get(0).getNome())))
                .andExpect(jsonPath("$[0].inativo", is(builderCliente().get(0).isInativo())))
                .andExpect(jsonPath("$[0].cpf", is(builderCliente().get(0).getCpf())))
                .andExpect(jsonPath("$[0].email", is(builderCliente().get(0).getEmail())))
                .andExpect(jsonPath("$[0].numeroTelefone", is(builderCliente().get(0).getNumeroTelefone())))
                .andExpect(jsonPath("$[1].id", is(builderCliente().get(1).getId())))
                .andExpect(jsonPath("$[1].nome", is(builderCliente().get(1).getNome())))
                .andExpect(jsonPath("$[1].inativo", is(builderCliente().get(1).isInativo())))
                .andExpect(jsonPath("$[1].cpf", is(builderCliente().get(1).getCpf())))
                .andExpect(jsonPath("$[1].email", is(builderCliente().get(1).getEmail())))
                .andExpect(jsonPath("$[1].numeroTelefone", is(builderCliente().get(1).getNumeroTelefone())));
    }

    @Test
    void testeMetodoConsultaClientePorId() throws Exception {
        when(repository.findById(any())).thenReturn(builderClienteOptional());

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", builderCliente().get(0).getId())).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect((ResultMatcher) jsonPath("$.id", is(1L))).
                andExpect((ResultMatcher) jsonPath( "$.nome", is(builderCliente().get(0).getNome()))).
                andExpect((ResultMatcher) jsonPath( "$.inativo", is(builderCliente().get(0).isInativo()))).
                andExpect((ResultMatcher) jsonPath("$.cpf", is(builderCliente().get(0).getCpf()))).
                andExpect((ResultMatcher) jsonPath( "$.email", is(builderCliente().get(0).getEmail()))).
                andExpect((ResultMatcher) jsonPath("$.numeroTelefone", is(builderCliente().get(0).getNumeroTelefone())));
    }

    @Test
    void deletarCliente() throws Exception {

        when(repository.findById(any())).thenReturn(builderClienteOptional());
        when(repository.save(any())).thenReturn(builderCliente().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", builderCliente().get(0).getId())).
                andExpect(status().isNoContent());
    }

    @Test
    void altararCliente() {
    }

    private List<ClienteEntity> builderCliente(){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(new ClienteEntity(1L, "Mariana", false, "456357159-17", "email@gmail.com", "(44)99874-8356"));
        clienteEntities.add(new ClienteEntity(3L, "João", false, "789456123-55", "", "(44)98747-5623"));
        return clienteEntities;
    }

    private Optional<ClienteEntity> builderClienteOptional(){
        return Optional.of(new ClienteEntity(1L, "Mariana", false, "456357159-17", "email@gmail.com", "(44)99874-8356"));
    }
}