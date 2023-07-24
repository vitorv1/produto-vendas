package com.example.produtovendas.service;

import com.example.produtovendas.dto.Produto;
import com.example.produtovendas.entity.ProdutoEntity;
import com.example.produtovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void cadastrar(Produto produto){
        repository.save(ProdutoEntity.atualizaDados(produto));
    }
}
