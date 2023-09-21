package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.example.produtovendas.builders.Builders.builderProdutoDomain;

class ProdutoValidationTest {

    @Test
    void testaSeValidacaoEstaLancandoException(){
        List<Produto> produtos = builderProdutoDomain();
        Assertions.assertThrows(RuntimeException.class, () -> ProdutoValidation.validaProduto(produtos, builderProdutoDomain().get(0)));
    }

    @Test
    void teteSeValidacaoProdutoInativoEstaLancandoException(){
        List<Produto> produtos = builderProdutoDomain();
        produtos.get(0).setInativo(true);
        Assertions.assertThrows(RuntimeException.class, () -> ProdutoValidation.validaProdutoInativo(produtos));
    }
}