package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produtos;
import com.example.produtovendas.entity.ProdutoEntity;
import com.example.produtovendas.entity.ProdutoMapper;
import com.example.produtovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository ;

    public void cadastrar(Produtos produto){
        repository.save(ProdutoMapper.paraEntity(produto));
    }
}
