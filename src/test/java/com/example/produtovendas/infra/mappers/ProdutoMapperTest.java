package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    @Test
    void testaSeRetornaUmProdutoDomain() {
        Long id = 1L;
        String nome = "Tenis";
        String marca = "Nike";
        double valor = 300;
        boolean inativo = false;

        Produto produto = ProdutoMapper.paraProduto(new ProdutoEntity(id, nome, inativo, marca, valor));

        Assertions.assertEquals(nome, produto.getNome());
        Assertions.assertEquals(id, produto.getId());
        Assertions.assertEquals(marca, produto.getMarca());
        Assertions.assertEquals(valor, produto.getValor());
        Assertions.assertEquals(inativo, produto.isInativo());
    }

    @Test
    void testaSeRetornaUmProdutoEntity() {
        Long id = 1L;
        String nome = "Tenis";
        String marca = "Nike";
        double valor = 300;
        boolean inativo = false;

        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(new Produto(id, nome, inativo, marca, valor));

        Assertions.assertEquals(nome, produtoEntity.getNome());
        Assertions.assertEquals(id, produtoEntity.getId());
        Assertions.assertEquals(marca, produtoEntity.getMarca());
        Assertions.assertEquals(valor, produtoEntity.getValor());
        Assertions.assertEquals(inativo, produtoEntity.isInativo());
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomains() {
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        Long id1 = 1L;
        String nome1 = "Tenis";
        String marca1 = "Nike";
        double valor1 = 300;
        boolean inativo1 = false;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "Vans";
        double valor2 = 400;
        boolean inativo2 = false;

        produtoEntities.add(new ProdutoEntity(id1, nome1, inativo1, marca1, valor1));
        produtoEntities.add(new ProdutoEntity(id2, nome2, inativo2, marca2, valor2));

        List<Produto> produtoList = ProdutoMapper.paraProdutos(produtoEntities);

        Produto produto1 = produtoList.get(0);
        Produto produto2 = produtoList.get(1);

        Assertions.assertEquals(id1, produto1.getId());
        Assertions.assertEquals(nome1, produto1.getNome());
        Assertions.assertEquals(marca1, produto1.getMarca());
        Assertions.assertEquals(valor1, produto1.getValor());
        Assertions.assertEquals(inativo1, produto1.isInativo());

        Assertions.assertEquals(id2, produto2.getId());
        Assertions.assertEquals(nome2, produto2.getNome());
        Assertions.assertEquals(marca2, produto2.getMarca());
        Assertions.assertEquals(valor2, produto2.getValor());
        Assertions.assertEquals(inativo2, produto2.isInativo());
    }

    @Test
    void paraEntitys() {
        List<Produto> produtos = new ArrayList<>();
        Long id1 = 1L;
        String nome1 = "Tenis";
        String marca1 = "Nike";
        double valor1 = 300;
        boolean inativo1 = false;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "Vans";
        double valor2 = 400;
        boolean inativo2 = false;

        produtos.add(new Produto(id1, nome1, inativo1, marca1, valor1));
        produtos.add(new Produto(id2, nome2, inativo2, marca2, valor2));

        List<ProdutoEntity> produtoList = ProdutoMapper.paraEntitys(produtos);

        ProdutoEntity produtoEntity1 = produtoList.get(0);
        ProdutoEntity produtoEntity2 = produtoList.get(1);

        Assertions.assertEquals(id1, produtoEntity1.getId());
        Assertions.assertEquals(nome1, produtoEntity1.getNome());
        Assertions.assertEquals(marca1, produtoEntity1.getMarca());
        Assertions.assertEquals(valor1, produtoEntity1.getValor());
        Assertions.assertEquals(inativo1, produtoEntity1.isInativo());

        Assertions.assertEquals(id2, produtoEntity2.getId());
        Assertions.assertEquals(nome2, produtoEntity2.getNome());
        Assertions.assertEquals(marca2, produtoEntity2.getMarca());
        Assertions.assertEquals(valor2, produtoEntity2.getValor());
        Assertions.assertEquals(inativo2, produtoEntity2.isInativo());
    }
}