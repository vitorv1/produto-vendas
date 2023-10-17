package com.example.produtovendas.controller;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import com.example.produtovendas.infra.repositories.VendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.produtovendas.validators.Validators.validaVendaController;
import static com.example.produtovendas.validators.Validators.validaVendasController;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendaRepository repository;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    void testeMetodoCadastroVenda() throws Exception {
        when(produtoRepository.findById(any())).thenReturn(Builders.builderProdutoOptionalEntity().get(0));
        when(clienteRepository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderVendaEntity().get(0));
        String json = Builders.builderJsonVenda();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/vendas").contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(MockMvcResultMatchers.status().isCreated());
        validaVendaController(resultActions);
    }

    @Test
    void testeMetodoBuscarPorId() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderVendaOptional().get(0));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/vendas/{id}", Builders.builderVendaEntity().get(0).getId())).
                andExpect(MockMvcResultMatchers.status().isOk());
        validaVendaController(resultActions);
    }

    @Test
    void testeMetodoBuscarTodos() throws Exception{
        when(repository.findAll()).thenReturn(Builders.builderVendaEntity());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/vendas")).
                andExpect(MockMvcResultMatchers.status().isOk());
        validaVendasController(resultActions, "0", Builders.builderVendaEntity().get(0));
        validaVendasController(resultActions, "1", Builders.builderVendaEntity().get(1));
    }

    @Test
    void testeMetodoDeletarVenda() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderVendaOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderVendaEntity().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/vendas/{id}", Builders.builderVendaEntity().get(0).getId())).
                andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testeMetodoAlterarVenda() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderVendaOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderVendaEntity().get(0));
        when(clienteRepository.findById(any())).thenReturn(Builders.builderClienteOptional().get(0));
        when(produtoRepository.findById(any())).thenReturn(Builders.builderProdutoOptionalEntity().get(0));

        String json = Builders.builderJsonVenda();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/vendas/{id}", Builders.builderVendaEntity().get(0).getId()).contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(MockMvcResultMatchers.status().isOk());

        validaVendaController(resultActions);
    }

}