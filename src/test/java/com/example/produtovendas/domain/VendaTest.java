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
        Long id = 2L;
        Cliente cliente = new Cliente(1L, "Vitor", false, "123456789-99", "email@gmail.com", "(44)99874-8356");
        Long idCliente = 1L;
        double valor = 350;
        Integer desconto = 10;
        List<Produto> produtos = new ArrayList<>();
        LocalDate data = LocalDate.now();

        Produto produto1 = new Produto(3L, "Tenis", false, "Nike", 220);
        Produto produto2 = new Produto(5L, "Camiseta", false, "Vans", 330);

        produtos.add(produto1);
        produtos.add(produto2);

        List<Produto> produtoList = new ArrayList<>();

        Venda venda = new Venda(id, new Cliente(), 2L, 0, false, 0, produtoList, LocalDate.now());

        Venda vendaDto = new Venda(null, cliente, idCliente, valor, false, desconto, produtos, data);

        venda.atualizaDados(vendaDto);

        Assertions.assertEquals(id ,venda.getId());
        Assertions.assertEquals(cliente, venda.getCliente());
        Assertions.assertEquals(idCliente, venda.getIdCliente());
        Assertions.assertEquals(valor, venda.getValor());
        Assertions.assertEquals(desconto, venda.getDesconto());
        Assertions.assertEquals(produtos, venda.getListaProdutos());
        Assertions.assertEquals(data, venda.getDataVenda());
        Assertions.assertFalse(venda.isInativo());
    }
}