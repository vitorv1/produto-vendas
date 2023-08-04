package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.entity.ProdutoEntity;
import com.example.produtovendas.entity.ProdutoMapper;
import com.example.produtovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto cadastroProduto(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        repository.save(produtoEntity);
        return ProdutoMapper.paraProduto(repository.findById(produtoEntity.getId()).get());
    }

    public Produto consultarProdutoPorId(Long id) {
        ProdutoEntity produtoEntity = repository.findById(id).get();
        if(produtoEntity.getInativo()){
            throw new RuntimeException("Produto inativo");
        }else {
            return ProdutoMapper.paraProduto(produtoEntity);
        }
    }

    public List<Produto> consultaTodosProdutos() {
        List<ProdutoEntity> produtoEntities = repository.findAll().stream().filter(ProdutoEntity -> !ProdutoEntity.getInativo()).toList();
        return ProdutoMapper.paraProdutos(produtoEntities);
    }

    public void deletarProduto(Long id) {
        ProdutoEntity produtoEntity = repository.findById(id).get();
        produtoEntity.setInativo(true);
        repository.save(produtoEntity);
    }

    public Produto alterarProduto(Long id, Produto produto) {
        ProdutoEntity produtoEntity = repository.findById(id).get();
        produtoEntity.atualizaDados(produto.getNome(), produto.getMarca(), produto.getValor());
        repository.save(produtoEntity);
        return ProdutoMapper.paraProduto(produtoEntity);
    }


}
