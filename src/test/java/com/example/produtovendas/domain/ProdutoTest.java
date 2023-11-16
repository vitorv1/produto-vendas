package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProdutoTest {

    /*@Test
    void testeMetodoAtualizaDados(){
        Produto produto = Builders.builderProdutoDomain().get(0);
        Produto produtoDto = Builders.builderProdutoDomain().get(1);
        produtoDto.setId(null);

        produto.atualizaDados((ProdutoDto) MapperManager.getInstance(3).paraDtoDeDomain(produtoDto));

        Validators.validaProdutoDomain(produto, 1);
    }

    @Test
    void testeMetodoInativar() {
        Produto produto = Builders.builderProdutoDomain().get(0);
        produto.inativar();
        Assertions.assertTrue(produto.isInativo());
    }*/
}