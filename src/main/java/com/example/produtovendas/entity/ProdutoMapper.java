package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Produtos;

public class ProdutoMapper {


    public static Produtos paraProdutos(ProdutoEntity produtoEntity){
        return new Produtos(produtoEntity);
    }

    public static ProdutoEntity paraEntity(Produtos produtos){
        return new ProdutoEntity(produtos);
    }
}
