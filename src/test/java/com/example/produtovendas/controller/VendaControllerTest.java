package com.example.produtovendas.controller;

import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import com.example.produtovendas.infra.repositories.VendaRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.hibernate.exception.spi.ViolatedConstraintNameExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.geom.RectangularShape;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(produtoRepository.findById(any())).thenReturn(Optional.of(new ProdutoEntity()));
        when(clienteRepository.findById(any())).thenReturn(Optional.of(new ClienteEntity()));
        when(repository.save(any())).thenReturn(builderVenda().get(0));
        String json = builderJson();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/vendas").contentType(MediaType.APPLICATION_JSON).content(json)).
                andExpect(MockMvcResultMatchers.status().isCreated());
        validaVenda(resultActions);
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void buscarTodos() {
    }

    @Test
    void deletarVenda() {
    }

    @Test
    void alterarVenda() {
    }

    private void validaVenda(ResultActions resultActions) throws Exception{
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(builderVenda().get(0).getId())).
                andExpect(MockMvcResultMatchers.jsonPath("$.clienteEntity").value(builderVenda().get(0).getClienteEntity())).
                andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(builderVenda().get(0).isInativo())).
                andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(builderVenda().get(0).getValor())).
                andExpect(MockMvcResultMatchers.jsonPath("$.desconto").value(builderVenda().get(0).getDesconto())).
                andExpect(MockMvcResultMatchers.jsonPath("$.listaProduto").value(builderVenda().get(0).getListaProdutos())).
                andExpect(MockMvcResultMatchers.jsonPath("$.dataVenda").value(builderVenda().get(0).getDataVenda()));
    }

    private void validaVendas(ResultActions resultActions, String indexListVenda, VendaEntity vendaEntity) throws Exception{
        String index = "$[".concat(indexListVenda).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(vendaEntity.getId())).
                andExpect(jsonPath(index.concat("clienteEntity")).value(vendaEntity.getClienteEntity())).
                andExpect(jsonPath(index.concat("inativo")).value(vendaEntity.isInativo())).
                andExpect(jsonPath(index.concat("valor")).value(vendaEntity.getValor())).
                andExpect(jsonPath(index.concat("desconto")).value(vendaEntity.getDesconto())).
                andExpect(jsonPath(index.concat("listaProdutos")).value(vendaEntity.getListaProdutos())).
                andExpect(jsonPath(index.concat("dataVenda")).value(vendaEntity.getDataVenda()));
    }

    private List<Optional<VendaEntity>> builderVendaOptional(){
        List<Optional<VendaEntity>> vendaEntities = new ArrayList<>();
        vendaEntities.add(Optional.of(builderVenda().get(0)));
        vendaEntities.add(Optional.of(builderVenda().get(1)));
        return vendaEntities;
    }

    private List<VendaEntity> builderVenda(){
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(new VendaEntity(2L, new ClienteEntity(), false, 120, 10, Collections.emptyList(), LocalDate.now()));
        vendaEntities.add(new VendaEntity(7L, new ClienteEntity(), false, 520, 0, Collections.emptyList(), LocalDate.now()));
        return vendaEntities;
    }

    private String builderJson(){
        return "{\"idCliente\": 12, \"desconto\":10, \"listaProdutos\":[{ \"id\": 3},{\"id\": 7}]}";
    }
}