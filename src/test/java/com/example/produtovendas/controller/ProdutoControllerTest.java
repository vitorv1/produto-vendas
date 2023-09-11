package com.example.produtovendas.controller;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
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
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository repository;


    @Test
    void testeMetodoCadastroProduto() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(Builders.builderProduto().get(0));

        String json = builderJson();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/produtos").contentType(MediaType.APPLICATION_JSON).content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        validaProduto(resultActions);
    }

    @Test
    void testeMetodoConsultarProdutoPorId() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptional().get(0));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", Builders.builderProduto().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        validaProduto(resultActions);
    }

    @Test
    void testeMetodoConsultarTodosProdutos() throws Exception {
        when(repository.findAll()).thenReturn(Builders.builderProduto());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/produtos"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        validaProdutos(resultActions, "0", Builders.builderProduto().get(0));
        validaProdutos(resultActions, "1", Builders.builderProduto().get(1));
    }

    @Test
    void testeMetodoDeletarProduto() throws Exception {
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptional().get(0));
        when(repository.save(any())).thenReturn(Builders.builderProduto().get(0));

        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", Builders.builderProduto().get(0).getId())).
                andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testeMetodoAlterarProduto() throws Exception{
        when(repository.findById(any())).thenReturn(Builders.builderProdutoOptional().get(1));
        when(repository.save(any())).thenReturn(Builders.builderProduto().get(0));
        String json = builderJson();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", Builders.builderProduto().get(0).getId()).contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(MockMvcResultMatchers.status().isOk());
        validaProduto(resultActions);
    }

    private void validaProduto(ResultActions resultActions) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderProduto().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(Builders.builderProduto().get(0).getNome()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderProduto().get(0).isInativo()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.marca").value(Builders.builderProduto().get(0).getMarca()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(Builders.builderProduto().get(0).getValor()));
    }

    private void validaProdutos(ResultActions resultActions, String indexProdutoList, ProdutoEntity produto)throws Exception{
        String index = "$[".concat(indexProdutoList).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(produto.getId()))
                .andExpect(jsonPath(index.concat("nome")).value(produto.getNome()))
                .andExpect(jsonPath(index.concat("inativo")).value(produto.isInativo()))
                .andExpect(jsonPath(index.concat("marca")).value(produto.getMarca()))
                .andExpect(jsonPath(index.concat("valor")).value(produto.getValor()));
    }

    private String builderJson(){
        return "{\"nome\": \"Tenis\", \"marca\":\"Nike\", \"valor\": 320}";
    }
}