package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.infra.entities.EstoqueEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.EstoqueMapper;
import com.example.produtovendas.infra.repositories.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EstoqueDataPovider {

    private final EstoqueRepository repository;


    public Estoque salvar(Estoque estoque){
        EstoqueEntity estoqueEntity = EstoqueMapper.paraEntity(estoque);

        try {
            estoqueEntity = repository.save(estoqueEntity);
        }catch (Exception ex){
            log.error("Erro ao cadastrar no estoque", ex);
            throw new BancoDeDadosException("Erro ao cadastrar no estoque");
        }

        return EstoqueMapper.paraDomain(estoqueEntity);
    }
}
