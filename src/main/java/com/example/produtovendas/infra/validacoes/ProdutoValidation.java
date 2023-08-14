package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;

import javax.crypto.interfaces.PBEKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProdutoValidation {

    public static void validaProduto(List<Produto> produtos, Produto produto){
        Optional<Produto> produtoOptional = produtos.stream().filter(Produto -> Produto.getNome().equalsIgnoreCase(produto.getNome())
                && Produto.getMarca().equalsIgnoreCase(produto.getMarca())
                && Produto.getValor() == produto.getValor()).findFirst();
        produtoOptional.ifPresent(Produto -> {throw new RuntimeException("Produto já existe no banco de dados");});
    }
}
