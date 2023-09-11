package com.example.produtovendas.controller;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.repositories.ClienteRepository;
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

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository repository;


    @Test
    void testeMetodoCadastroCliente() throws Exception {

        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(Builders.builderCliente().get(0));

        String clienteJson = builderJson();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson));

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        validaCliente(result);
    }

    @Test
    void testeMetodoConsultarTodosClientes() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderCliente());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        validarAtributosDeCliente(resultActions, "0", Builders.builderCliente().get(0));
        validarAtributosDeCliente(resultActions, "1", Builders.builderCliente().get(1));
    }

    @Test
    void testeMetodoConsultaClientePorId() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", Builders.builderCliente().get(0).getId())).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON));
        validaCliente(resultActions);
    }

    @Test
    void testeMetodoDeletarCliente() throws Exception {

        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderCliente().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", Builders.builderCliente().get(0).getId())).
                andExpect(status().isNoContent());
    }

    @Test
    void testeMetodoAltararCliente() throws Exception {

        when(repository.findById(any())).thenReturn(Builders.builderClienteOptional().get(1));
        when(repository.save(any())).thenReturn(Builders.builderCliente().get(0));
        String json = builderJson();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", Builders.builderCliente().get(1).getId()).contentType(MediaType.APPLICATION_JSON).content(json)).
        andExpect(status().isOk());
        validaCliente(resultActions);
    }

    @Test
    void testeMetodoCadastroClienteEstaLancandoException() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderCliente());
        when(repository.save(any())).thenReturn(Builders.builderCliente().get(0));

        String clienteJson = builderJson();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson)).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    public void validarAtributosDeCliente(ResultActions resultActions, String indexClienteList, ClienteEntity cliente) throws Exception {
        String index = "$[".concat(indexClienteList).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(cliente.getId()))
                .andExpect(jsonPath(index.concat("nome")).value(cliente.getNome()))
                .andExpect(jsonPath(index.concat("inativo")).value(cliente.isInativo()))
                .andExpect(jsonPath(index.concat("cpf")).value(cliente.getCpf()))
                .andExpect(jsonPath(index.concat("email")).value(cliente.getEmail()))
                .andExpect(jsonPath(index.concat("numeroTelefone")).value(cliente.getNumeroTelefone()));
    }

    private String builderJson(){
        return "{\"nome\": \"Mariana\", \"cpf\":\"456357159-17\", \"email\": \"email@gmail.com\", \"numeroTelefone\": \"(44)99874-8356\"}";
    }

    private void validaCliente(ResultActions resultActions) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderCliente().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(Builders.builderCliente().get(0).getNome()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderCliente().get(0).isInativo()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(Builders.builderCliente().get(0).getCpf()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefone").value(Builders.builderCliente().get(0).getNumeroTelefone()));
    }
}