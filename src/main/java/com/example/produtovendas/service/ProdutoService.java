package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
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
            throw new BancoDeDadosException("Erro no banco de dados");
        }try {
            return ProdutoMapper.paraProduto(repository.findById(produtoEntity.getId()).get());
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }

    }

    public Produto consultarProdutoPorId(Long id) throws Exception{
        ProdutoEntity produtoEntity;
        try {
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        if(produtoEntity.getInativo()){
            throw new RuntimeException("Produto inativo");
        }else {
            return ProdutoMapper.paraProduto(produtoEntity);
        }
    }

    public List<Produto> consultaTodosProdutos() throws SQLException{
        List<ProdutoEntity> produtoEntities;
        try{
            produtoEntities = repository.findAll().stream().filter(ProdutoEntity -> !ProdutoEntity.getInativo()).toList();
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        return ProdutoMapper.paraProdutos(produtoEntities);
    }

    public void deletarProduto(Long id) throws SQLException {
        ProdutoEntity produtoEntity;
        try {
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        produtoEntity.setInativo(true);
        try{
            repository.save(produtoEntity);
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
    }

    public Produto alterarProduto(Long id, Produto produto) throws SQLException {
        ProdutoEntity produtoEntity;
        try{
             produtoEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        produtoEntity.atualizaDados(produto.getNome(), produto.getMarca(), produto.getValor());
        try{
            repository.save(produtoEntity);
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        return ProdutoMapper.paraProduto(produtoEntity);
    }


}
