package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataProvider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueDataProvider estoqueDataProvider;
    private final ProdutoDataProvider produtoDataProvider;

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque(null, produto.getQuantidade(), produto);
        estoqueDataProvider.salvar(estoque);
    }

    public List<Produto> definirQuantidadeEstoque(List<Produto> produtoList){
        System.out.println("oi");
        System.out.println(produtoList);
        return produtoList.stream().map(produto -> alterarQuantidadeProduto(produto.getId())).collect(Collectors.toList());
    }

    private Produto alterarQuantidadeProduto(Long id){
        Produto produto = produtoDataProvider.consultarPorId(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setQuantidade(produto.getQuantidade() - 1);
        return produtoDataProvider.salvar(produto);
    }
}
