package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.repositories.EstoqueRepository;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProdutoDataProvider {

    private final ProdutoRepository repository;
    private final EstoqueRepository estoqueRepository;

    public Produto salvar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(produto);
        try {
            produtoEntity = repository.save(produtoEntity);
        } catch (Exception ex) {
            log.error("Erro no cadastro do produto", ex);
            throw new BancoDeDadosException("Erro no cadastro do produto");
        }
        return ProdutoMapper.paraProduto(produtoEntity);
    }

    public Optional<Produto> consultarPorId(Long id) {
        Optional<ProdutoEntity> produtoEntity;
        try {
            produtoEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error("Erro na consalta por id", ex);
            throw new BancoDeDadosException("Erro na consalta por id");
        }

        return produtoEntity.isEmpty() ? Optional.empty() : Optional.of(ProdutoMapper.paraProduto(produtoEntity.get()));
    }

    public List<Produto> consultaTodos() {
        try {
            return ProdutoMapper.paraProdutos(repository.findAll());
        } catch (Exception ex) {
            log.error("Erro na consulta por todos", ex);
            throw new BancoDeDadosException("Erro na consulta por todos");
        }
    }


}
