package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void testeMetodoInativar() {
        Venda venda = Builders.builderVendaDomain().get(0);
        venda.inativar();
        Assertions.assertTrue(venda.isInativo());
    }

    @Test
    void atualizaDados() {
        Long id = 2L;
        Cliente cliente = Builders.builderClienteDomain().get(0);
        Long idCliente = 1L;
        double valor = 350;
        Integer desconto = 10;
        List<Produto> produtos = Builders.builderProdutoDomain(); ;
        LocalDate data = LocalDate.now();

        Venda venda = Builders.builderVendaDomain().get(0);

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