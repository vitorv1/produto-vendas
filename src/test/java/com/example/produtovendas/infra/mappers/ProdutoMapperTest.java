package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    @Test
    void testaSeRetornaUmProdutoDomain() {
        Produto produto = ProdutoMapper.paraProduto(Builders.builderProduto().get(0));
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(ProdutoMapper.paraEntity(produto));
        validaProduto(produtoEntities);
    }

    @Test
    void testaSeRetornaUmProdutoEntity() {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(Builders.builderProdutoDomain());
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(produtoEntity);
        validaProduto(produtoEntities);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomains() {
        List<Produto> produtoList = ProdutoMapper.paraProdutos(Builders.builderProduto());
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(ProdutoMapper.paraEntity(produtoList.get(0)));
        produtoEntities.add(ProdutoMapper.paraEntity(produtoList.get(1)));
        validaProduto(produtoEntities);
    }

    @Test
    void paraEntitys() {
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

    private void validaProduto(List<ProdutoEntity> produtoEntities){
        for (int i = 0; i < produtoEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderProduto().get(i).getId(), produtoEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderProduto().get(i).getNome(), produtoEntities.get(i).getNome());
            Assertions.assertFalse(produtoEntities.get(0).isInativo());
            Assertions.assertEquals(Builders.builderProduto().get(i).getMarca(), produtoEntities.get(i).getMarca());
            Assertions.assertEquals(Builders.builderProduto().get(i).getValor(), produtoEntities.get(i).getValor());
        }
    }
}