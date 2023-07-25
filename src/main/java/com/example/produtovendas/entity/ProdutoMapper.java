package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Produto;

public class ProdutoMapper {


    public static Produto paraProdutos(ProdutoEntity produtoEntity){
        return new Produto(produtoEntity);
    }

    public static ProdutoEntity paraEntity(Produto produtos){
        return new ProdutoEntity(produtos);
    }
}
