package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoDataProvider {

    @Autowired
    private final ProdutoRepository repository;

    public Produto cadastroProduto(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        try {
            repository.save(produtoEntity);
            return ProdutoMapper.paraProduto(repository.findById(produtoEntity.getId()).get());
        }catch (Exception ex) {
            throw new BancoDeDadosException("Erro no cadastro do produto");
        }
    }

    public Produto consultarProdutoPorId(Long id) {
        try {
            return ProdutoMapper.paraProduto(repository.findById(id).get());
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro na consalta por id");
        }
    }

    public List<Produto> consultaTodosProdutos() {
        try{
            return ProdutoMapper.paraProdutos(repository.findAll());
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro na consulta por todos");
        }
    }

    public void deletarProduto(Long id)  {
        Produto produto;
        try {
            produto  = ProdutoMapper.paraProduto(repository.findById(id).get());
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro na busca de produto");
        }
        produto.inativar();
        try{
            repository.save(ProdutoMapper.paraEntity(produto));
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro na atualização do produto");
        }
    }

    public Produto alterarProduto(Long id, Produto produtoDto)  {
        Produto produto;
        try{
            produto = ProdutoMapper.paraProduto(repository.findById(id).get());
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro na consulta por id");
        }
        produto.atualizaDados(produtoDto.getNome(), produtoDto.getMarca(),  produtoDto.getValor());
        try{
            repository.save(ProdutoMapper.paraEntity(produto));
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro na atualização do produto");
        }
        return produto;
    }

}
