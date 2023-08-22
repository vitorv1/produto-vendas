package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoDataProvider produtoDataProvider;

    public Produto cadastroProduto(Produto produto){
        return produtoDataProvider.salvarProduto(produto);
    }

    public Produto consultarProdutoPorId(Long id){
        return buscarProdutoPorId(id);
    }

    public List<Produto> consultaTodosProdutos() {
        return produtoDataProvider.consultaTodosProdutos();
    }

    public void deletarProduto(Long id){
        produtoDataProvider.deletarProduto(id);
    }

    public Produto alterarProduto(Long id, Produto produtoDto){
        return produtoDataProvider.alterarProduto(id, produtoDto);
    }

    private Produto buscarProdutoPorId(Long id){
        return produtoDataProvider.consultarProdutoPorId(id).orElseThrow(()-> new RuntimeException("Produto não existe"));
    }


}
