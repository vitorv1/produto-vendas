package com.example.produtovendas.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.entity.ProdutoEntity;
import com.example.produtovendas.entity.ProdutoMapper;
import com.example.produtovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto cadastroProduto(Produto produto) throws SQLException{
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        try {
            repository.save(produtoEntity);
        }catch (Exception ex){
            throw new SQLException();
        }try {
            return ProdutoMapper.paraProduto(repository.findById(produtoEntity.getId()).get());
        }catch (Exception ex){
            throw new SQLException();
        }

    }

    public Produto consultarProdutoPorId(Long id) throws Exception{
        ProdutoEntity produtoEntity;
        try {
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new SQLException();
        }
        if(produtoEntity.getInativo()){
            throw new RuntimeException("Produto inativo");
        }else {
            return ProdutoMapper.paraProduto(produtoEntity);
        }
    }

    public List<Produto> consultaTodosProdutos() throws Exception{
        List<ProdutoEntity> produtoEntities;
        try{
            produtoEntities = repository.findAll().stream().filter(ProdutoEntity -> !ProdutoEntity.getInativo()).toList();
        }catch (Exception ex){
            throw new SQLException();
        }
        return ProdutoMapper.paraProdutos(produtoEntities);
    }

    public void deletarProduto(Long id) throws Exception {
        ProdutoEntity produtoEntity;
        try {
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new SQLException();
        }
        produtoEntity.setInativo(true);
        try{
            repository.save(produtoEntity);
        }catch (Exception ex){
            throw new SQLException();
        }
    }

    public Produto alterarProduto(Long id, Produto produto) throws Exception {
        ProdutoEntity produtoEntity;
        try{
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new SQLException();
        }
        produtoEntity.atualizaDados(produto.getNome(), produto.getMarca(), produto.getValor());
        try{
            repository.save(produtoEntity);
        }catch (Exception ex){
            throw new SQLException();
        }
        return ProdutoMapper.paraProduto(produtoEntity);
    }


}
