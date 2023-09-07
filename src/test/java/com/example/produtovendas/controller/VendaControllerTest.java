package com.example.produtovendas.controller;

import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaControllerTest {

    @Test
    void cadastroVenda() {
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

    private List<VendaEntity> builderVenda(){
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add()
    }
    private String builderJson(){
        return "{\"idCliente\": 12, \"desconto\":10, \"listaProdutos\":[{ \"id\": 3},{\"id\": 7}]}";
    }
}