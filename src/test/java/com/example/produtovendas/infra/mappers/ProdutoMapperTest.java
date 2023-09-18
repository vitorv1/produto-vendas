package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.produtovendas.validators.Validators.*;

class ProdutoMapperTest {

    @Test
    void testaSeRetornaUmProdutoDomain() {
        Produto produto = ProdutoMapper.paraProduto(Builders.builderProdutoEntity().get(0));
        validaProdutoDomain(produto, 0);
    }

    @Test
    void testaSeRetornaUmProdutoEntity() {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(Builders.builderProdutoDomain().get(0));
        validaProdutoEntity(produtoEntity, 0);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomains() {
        List<Produto> produtoList = ProdutoMapper.paraProdutos(Builders.builderProdutoEntity());
        validaProdutoDomain(produtoList.get(0), 0);
        validaProdutoDomain(produtoList.get(1), 1);
    }

    @Test
    void testeSeRetornaUmProdutoEntitys(){
        List<ProdutoEntity> produtoList = ProdutoMapper.paraEntitys(Builders.builderProdutoDomain());
        validaProdutoEntity(produtoList.get(0), 0);
        validaProdutoEntity(produtoList.get(1), 1);
    }
}