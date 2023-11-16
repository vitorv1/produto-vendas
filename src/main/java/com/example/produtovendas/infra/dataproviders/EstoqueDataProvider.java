package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.infra.entities.EstoqueEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.mappers.EstoqueMapper;
import com.example.produtovendas.infra.repositories.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EstoqueDataProvider {

    private final EstoqueRepository repository;

    private static final String MENSAGEM_ERRO_SALVAR_ESTOQUE = "Erro ao cadastrar no estoque";


    public Estoque salvar(Estoque estoque){
        EstoqueEntity estoqueEntity = EstoqueMapper.paraEntity(estoque);

        try {
            estoqueEntity = repository.save(estoqueEntity);
        }catch (Exception ex){
            log.error(MENSAGEM_ERRO_SALVAR_ESTOQUE, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_SALVAR_ESTOQUE);
        }

        return EstoqueMapper.paraDomain(estoqueEntity);
    }
}
