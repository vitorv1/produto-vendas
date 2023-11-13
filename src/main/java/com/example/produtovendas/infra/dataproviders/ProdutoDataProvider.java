package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProdutoDataProvider {

    private final ProdutoRepository repository;

    private static final String MENSAGEM_ERRO_SALVAR_PRODUTO = "Erro no cadastro do produto";
    private static final String MENSAGEM_ERRO_CONSULTA_ID_PRODUTO = "Erro na consalta por id";
    private static final String MENSAGEM_ERRO_CONSULTA_TODOS_PRODUTOS = "Erro na consulta por todos";

    public Produto salvar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        try {
            produtoEntity = repository.save(produtoEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR_PRODUTO, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_SALVAR_PRODUTO);
        }
        return ProdutoMapper.paraDomain(produtoEntity);
    }

    public Optional<Produto> consultarPorId(Long id) {
        Optional<ProdutoEntity> produtoEntity;
        try {
            produtoEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTA_ID_PRODUTO, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CONSULTA_ID_PRODUTO);
        }

        return produtoEntity.isEmpty() ? Optional.empty() : Optional.of(ProdutoMapper.paraDomain(produtoEntity.get()));
    }

    public List consultaTodos() {
        try {
            return ProdutoMapper.paraDomains(repository.findAll());
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTA_TODOS_PRODUTOS, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CONSULTA_TODOS_PRODUTOS);
        }
    }


}
