package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    public static Produto paraProduto(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .valor(produtoEntity.getValor())
                .marca(produtoEntity.getMarca())
                .inativo(produtoEntity.isInativo())
                .quantidade(produtoEntity.getQuantidade())
                .build();
    }

    public static ProdutoEntity paraEntity(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .marca(produto.getMarca())
                .inativo(produto.isInativo())
                .quantidade(produto.getQuantidade())
                .build();
    }

    public static List<Produto> paraProdutos(List<ProdutoEntity> produtoEntities) {
        return produtoEntities.stream().map(ProdutoMapper::paraProduto).toList();
    }

    public static List<ProdutoEntity> paraEntitys(List<Produto> produtoList) {
        return produtoList.stream().map(ProdutoMapper::paraEntity).toList();
    }
}
