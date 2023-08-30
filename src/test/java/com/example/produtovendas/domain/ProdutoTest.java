package com.example.produtovendas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testeMetodoAtualizaDados(){
        Long id = 3L;
        String nome = "Calça";
        String marca = "Vans";
        double valor = 300;
        Produto produto = new Produto(id, "Tenis", false, "Nike", 250);
        Produto produtoDto = new Produto(null, nome, false, marca, valor);

        produto.atualizaDados(produtoDto);

        Assertions.assertEquals(id, produto.getId());
        Assertions.assertEquals(nome, produto.getNome());
        Assertions.assertFalse(produto.isInativo());
        Assertions.assertEquals(marca, produto.getMarca());
        Assertions.assertEquals(valor, produto.getValor());
    }

    @Test
    void testeMetodoInativar() {
        Produto produto = new Produto(1L, "Tenis", false, "Nike", 250);
        produto.inativar();
        Assertions.assertTrue(produto.isInativo());
    }
}