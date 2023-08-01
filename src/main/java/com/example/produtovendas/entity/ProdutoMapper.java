package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    public static Produto paraProduto(ProdutoEntity produtoEntity) {
        return new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getMarca(), produtoEntity.getValor());
    }

    public static ProdutoEntity paraEntity(Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getMarca(), produto.getValor());
    }

    public static List<Produto> paraProdutos(List<ProdutoEntity> produtoEntities) {
        List<Produto> produtoList = new ArrayList<>();
        for (ProdutoEntity produtoEntity : produtoEntities) {
            produtoList.add(paraProduto(produtoEntity));
        }
        return produtoList;
    }

    public static List<ProdutoEntity> paraEntitys(List<Produto> produtoList) {
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        for (Produto produto : produtoList) {
            produtoEntities.add(paraEntity(produto));
        }
        return produtoEntities;
    }
}
