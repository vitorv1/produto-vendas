package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testeMetodoAtualizaDados(){
        Long id = 1L;
        String nome = "Calça";
        String marca = "Vans";
        double valor = 300;
        Produto produto = Builders.builderProdutoDomain().get(0);
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
        Produto produto = Builders.builderProdutoDomain().get(0);
        produto.inativar();
        Assertions.assertTrue(produto.isInativo());
    }
}