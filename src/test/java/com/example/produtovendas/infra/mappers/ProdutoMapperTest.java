package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.produtovendas.validators.Validators.validaProdutoEntity;

class ProdutoMapperTest {

    @Test
    void testaSeRetornaUmProdutoDomain() {
        Produto produto = ProdutoMapper.paraProduto(Builders.builderProduto().get(0));
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(ProdutoMapper.paraEntity(produto));
        validaProdutoEntity(produtoEntities);
    }

    @Test
    void testaSeRetornaUmProdutoEntity() {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(Builders.builderProdutoDomain().get(0));
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(produtoEntity);
        validaProdutoEntity(produtoEntities);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomains() {
        List<Produto> produtoList = ProdutoMapper.paraProdutos(Builders.builderProduto());
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(ProdutoMapper.paraEntity(produtoList.get(0)));
        produtoEntities.add(ProdutoMapper.paraEntity(produtoList.get(1)));
        validaProdutoEntity(produtoEntities);
    }

    @Test
    void paraEntitys(){
        List<ProdutoEntity> produtoList = ProdutoMapper.paraEntitys(Builders.builderProdutoDomain());
        validaProdutoEntity(produtoList);
    }
}