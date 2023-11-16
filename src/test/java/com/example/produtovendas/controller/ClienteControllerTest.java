package com.example.produtovendas.controller;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository repository;


    @Test
    void testeMetodoCadastroCliente() throws Exception {

        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(Builders.builderClienteEntity().get(0));

        String clienteJson = Builders.builderJsonCliente();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson));

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        Validators.validaClienteController(result);
    }

    @Test
    void testeMetodoConsultarTodosClientes() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderClienteEntity());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        validarAtributosDeCliente(resultActions, "0", Builders.builderClienteEntity().get(0));
        validarAtributosDeCliente(resultActions, "1", Builders.builderClienteEntity().get(1));
    }

    @Test
    void testeMetodoConsultaClientePorId() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", Builders.builderClienteEntity().get(0).getId())).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON));
        Validators.validaClienteController(resultActions);
    }

    @Test
    void testeMetodoDeletarCliente() throws Exception {

        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderClienteEntity().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", Builders.builderClienteEntity().get(0).getId())).
                andExpect(status().isNoContent());
    }

    @Test
    void testeMetodoAltararCliente() throws Exception {

        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(1));
        when(repository.save(any())).thenReturn(Builders.builderClienteEntity().get(0));
        String json = Builders.builderJsonCliente();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", Builders.builderClienteEntity().get(1).getId()).contentType(MediaType.APPLICATION_JSON).content(json)).
        andExpect(status().isOk());
        Validators.validaClienteController(resultActions);
    }

    @Test
    void testeMetodoCadastroClienteEstaLancandoException() throws Exception {
        when(repository.findByCpf(any())).thenReturn(Builders.builderClienteOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderClienteEntity().get(0));

        String clienteJson = Builders.builderJsonCliente();

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson)).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }*/
}