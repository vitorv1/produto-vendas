package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.infra.entities.ProdutoEntity;

import java.util.List;

public abstract class ProdutoMapper{

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


    public static Produto paraDomain(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .id(produtoEntity.getId())
                .nome(produtoEntity.getNome())
                .valor(produtoEntity.getValor())
                .marca(produtoEntity.getMarca())
                .inativo(produtoEntity.isInativo())
                .quantidade(produtoEntity.getQuantidade())
                .build();
    }

    public static Produto paraDomainDeDto(ProdutoDto produtoDto) {
        return Produto.builder()
                .nome(produtoDto.nome())
                .quantidade(produtoDto.quantidade())
                .marca(produtoDto.marca())
                .valor(produtoDto.valor())
                .build();
    }

    public static ProdutoDto paraDtoDeDomain(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .quantidade(produto.getQuantidade())
                .valor(produto.getValor())
                .build();
    }

    public static List<ProdutoEntity> paraEntitys(List<Produto> produtoList) {
        return produtoList.stream().map(ProdutoMapper::paraEntity).toList();
    }

    public static List<Produto> paraDomains(List<ProdutoEntity> produtoEntities){
        return produtoEntities.stream().map(ProdutoMapper::paraDomain).toList();
    }

    public static List<ProdutoDto> paraDtosDeDomains(List<Produto> produtoList) {
        return produtoList.stream().map(ProdutoMapper::paraDtoDeDomain).toList();
    }
}
