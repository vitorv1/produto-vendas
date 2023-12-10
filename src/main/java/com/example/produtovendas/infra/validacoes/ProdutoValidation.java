package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProdutoValidation {

    public static void validaProdutoExistente(List<Produto> produtos, Produto produto) {
        Optional<Produto> produtoOptional = produtos.stream().filter(produtoTeste ->
                produtoTeste.getNome().equalsIgnoreCase(produto.getNome())
                        && produtoTeste.getMarca().equalsIgnoreCase(produto.getMarca())
                        && Objects.equals(produtoTeste.getValor(), produto.getValor())).findFirst();
        produtoOptional.ifPresent(produtoTeste -> {
            throw new RuntimeException("Produto já existe no banco de dados");
        });
    }

    public static void validaProdutoInativo(List<Produto> produtos) {
        produtos.forEach(produto -> {
            if (produto.isInativo())
                throw new RuntimeException("Produto inativo, não é possível adiciona-lo a uma venda");
        });
    }
}
