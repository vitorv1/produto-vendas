package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ClienteEntity;
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
import java.util.Optional;

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

    public Optional<Produto> consultarProdutoPorId(Long id) {
        Optional<ProdutoEntity> produtoEntity;

        try {
            produtoEntity = repository.findById(id);
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro na consalta por id");
        }

        return produtoEntity.isEmpty() ? Optional.empty() : Optional.of(ProdutoMapper.paraProduto(produtoEntity.get()));
    }

    public List<Produto> consultaTodosProdutos() {
        try{
            return ProdutoMapper.paraProdutos(repository.findAll());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro na consulta todos os produtos");
        }
    }

    public Produto alterarProduto(Long id, Produto produtoDto){
        Optional<Produto> produto = consultarProdutoPorId(id);
        if(produto.isPresent()){
            produto.get().atualizaDados(produtoDto);
            try{
                return ProdutoMapper.paraProduto(repository.save(ProdutoMapper.paraEntity(produto.get())));
            }catch (Exception ex){
                throw new BancoDeDadosException("Erro ao salvar alterações");
            }
        }else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    public Produto deletarProduto(Long id){
        Optional<Produto> produto = consultarProdutoPorId(id);
        if(produto.isPresent()){
            produto.get().inativar();
            return ProdutoMapper.paraProduto(repository.save(ProdutoMapper.paraEntity(produto.get())));
        }else {
            throw new RuntimeException("Produto não encontrado");
        }
    }
}
