package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
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
        return ProdutoMapper.paraProduto(repository.getReferenceById(produtoEntity.getId()));
    }

    public Produto consultarProdutoPorId(Long id){
        return ProdutoMapper.paraProduto(repository.getReferenceById(id));
    }

    public List<Produto> consultaTodosProdutos() {
        return ProdutoMapper.paraProdutos(repository.findAll());
    }

    public void deletarProduto (Long id){
        repository.deleteById(id);
    }

    public Produto alterarProduto(Long id, Produto produto){
        ProdutoEntity produtoEntity = repository.getReferenceById(id);
        produtoEntity.atualizaDados(produto.getNome(), produto.getMarca(), produto.getValor());
        repository.save(produtoEntity);
        return ProdutoMapper.paraProduto(produtoEntity);
    }


}
