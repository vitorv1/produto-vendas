package com.example.produtovendas.controller;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.infra.repositories.EstoqueRepository;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

   /* @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository repository;

    @MockBean
    private EstoqueRepository estoqueRepository;


    @Test
    void testeMetodoCadastroProduto() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(Builders.builderProdutoEntity().get(0));
        when(estoqueRepository.save(any())).thenReturn(Builders.builderEstoqueEntity());
        String json = Builders.builderJsonProduto();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/produtos").contentType(MediaType.APPLICATION_JSON).content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        validaProdutoController(resultActions);
    }

    @Test
    void testeMetodoConsultarProdutoPorId() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptionalEntity().get(0));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", Builders.builderProdutoEntity().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        validaProdutoController(resultActions);
    }

    @Test
    void testeMetodoConsultarTodosProdutos() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderProdutoEntity());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        validaProdutosController(resultActions, "0", Builders.builderProdutoEntity().get(0));
        validaProdutosController(resultActions, "1", Builders.builderProdutoEntity().get(1));
    }

    @Test
    void testeMetodoDeletarProduto() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptionalEntity().get(0));
        when(repository.save(any())).thenReturn(Builders.builderProdutoEntity().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", Builders.builderProdutoEntity().get(0).getId())).
                andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testeMetodoAlterarProduto() throws Exception{
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptionalEntity().get(1));
        when(repository.save(any())).thenReturn(Builders.builderProdutoEntity().get(0));
        String json = Builders.builderJsonProduto();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", Builders.builderProdutoEntity().get(0).getId()).contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(MockMvcResultMatchers.status().isOk());
        validaProdutoController(resultActions);
    }

    @Test
    void testeMetodoCadastroProdutoEstaLancandoException() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderProdutoEntity());
        when(repository.save(any())).thenReturn(Builders.builderProdutoEntity().get(0));

        String json =   Builders.builderJsonProduto();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/produtos").contentType(MediaType.APPLICATION_JSON).content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testeMetodoCadastroProdutoEstaValidando() throws Exception{
        when(repository.findAll()).thenReturn(Builders.builderProdutoEntity());
        when(repository.save(any())).thenReturn(Builders.builderProdutoEntity().get(0));

        String json = Builders.builderJsonProduto();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/produtos").contentType(MediaType.APPLICATION_JSON).content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }*/
}