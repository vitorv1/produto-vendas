package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.infra.entities.EstoqueEntity;

public class EstoqueMapper {

    public static Estoque paraDomain(EstoqueEntity estoqueEntity){
        return Estoque.builder()
                .id(estoqueEntity.getId())
                .quantidade(estoqueEntity.getQuantidade())
                .produto(ProdutoMapper.paraProduto(estoqueEntity.getProdutoEntity()))
                .build();
    }

    public static EstoqueEntity paraEntity(Estoque estoque){
        return EstoqueEntity.builder()
                .id(estoque.getId())
                .quantidade(estoque.getQuantidade())
                .produtoEntity(ProdutoMapper.paraEntity(estoque.getProduto()))
                .build();
    }
}
