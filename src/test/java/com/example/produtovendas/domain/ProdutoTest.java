package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testeMetodoAtualizaDados(){
        Produto produto = Builders.builderProdutoDomain().get(0);
        Produto produtoDto = Builders.builderProdutoDomain().get(1);
        produtoDto.setId(null);

        produto.atualizaDados(produtoDto);

        Validators.validaProdutoDomainAlterado(produto);
    }

    @Test
    void testeMetodoInativar() {
        Produto produto = Builders.builderProdutoDomain().get(0);
        produto.inativar();
        Assertions.assertTrue(produto.isInativo());
    }
}