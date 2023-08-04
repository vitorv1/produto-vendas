package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    public static Produto paraProduto(ProdutoEntity produtoEntity) {
        return new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getMarca(), produtoEntity.getValor());
    }

    public static ProdutoEntity paraEntity(Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getMarca(), produto.getValor());
    }

    public static List<Produto> paraProdutos(List<ProdutoEntity> produtoEntities) {
      return produtoEntities.stream().map(ProdutoMapper :: paraProduto).collect(Collectors.toList());
    }

    public static List<ProdutoEntity> paraEntitys(List<Produto> produtoList) {
        return produtoList.stream().map(ProdutoMapper :: paraEntity).collect(Collectors.toList());
    }
}
