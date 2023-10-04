package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataPovider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueDataPovider estoqueDataPovider;
    private final ProdutoDataProvider produtoDataProvider;

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque(null, produto.getQuantidade(), produto);
        estoqueDataPovider.salvar(estoque);
    }

    public List<Produto> definirQuantidadeEstoque(List<Produto> produtoList){
        return produtoList.stream().map(this::ajustaQuantidade).collect(Collectors.toList());
    }

    public Produto ajustaQuantidade(Produto produto){
        produto.setQuantidade(produto.getQuantidade() - 1);
        return produto;
    }
}
