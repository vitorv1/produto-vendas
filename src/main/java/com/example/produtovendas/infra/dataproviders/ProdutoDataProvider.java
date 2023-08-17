package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProdutoDataProvider {


    private final ProdutoRepository repository;

    @Autowired
    public ProdutoDataProvider (ProdutoRepository repository){
        this.repository = repository;
    }

    public Produto salvarProduto(Produto produto) {
        List<ProdutoEntity> produtoEntities;
        try {
            produtoEntities = repository.findAll();
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao consultar todos no banco de dados na validação");
        }
        ProdutoValidation.validaProduto(ProdutoMapper.paraProdutos(produtoEntities), produto);
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        try {
            repository.save(produtoEntity);
            return ProdutoMapper.paraProduto(produtoEntity);
        }catch (Exception ex) {
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro no cadastro do produto");
        }
    }

    public Produto consultarProdutoPorId(Long id) {
        try {
            return ProdutoMapper.paraProduto(repository.findById(id).get());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro na consalta por id");
        }
    }

    public List<Produto> consultaTodosProdutos() {
        try{
            return ProdutoMapper.paraProdutos(repository.findAll());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro na consulta por todos");
        }
    }
}
