package com.example.produtovendas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void testeMetodoInativar() {
        List<Produto> produtoList = new ArrayList<>();
        Venda venda = new Venda(1L, new Cliente(), 2L, 0, false, 0, produtoList, LocalDate.now());
        venda.inativar();
        Assertions.assertTrue(venda.isInativo());
    }

    @Test
    void atualizaDados() {
    }
}