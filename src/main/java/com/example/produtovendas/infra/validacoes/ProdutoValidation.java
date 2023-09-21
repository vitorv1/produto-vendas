package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;

import java.util.List;
import java.util.Optional;

public class ProdutoValidation {

    public static void validaProduto(List<Produto> produtos, Produto produto) {
        Optional<Produto> produtoOptional = produtos.stream().filter(Produto ->
                Produto.getNome().equalsIgnoreCase(produto.getNome()) &&
                        Produto.getMarca().equalsIgnoreCase(produto.getMarca()) &&
                        Produto.getValor() == produto.getValor()).findFirst();
        produtoOptional.ifPresent(produtoTeste -> {
            throw new RuntimeException("Produto já existe no banco de dados");
        });
    }

    public static void validaProdutoInativo(List<Produto> produtos) {
        produtos.forEach(produto -> {
            if (produto.isInativo())
                throw new RuntimeException("Produto inativo, não é possível adicionalo a uma venda");
        });
    }
}
