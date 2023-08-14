package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoValidationTest {

    @Test
    void testaSeValidacaoEstaLancandoException(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Tenis", false, "Nike", 350));
        produtos.add(new Produto(2L, "Camiseta", false, "High", 400));
        Assertions.assertThrows(RuntimeException.class, () -> ProdutoValidation.validaProduto(produtos, new Produto(3L, "Tenis", false, "Nike", 350)));
    }
}