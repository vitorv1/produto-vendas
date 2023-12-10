package com.example.produtovendas.infra.mappers;
import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.infra.entities.EstoqueEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.produtovendas.builders.Builders.builderEstoqueDomain;
import static com.example.produtovendas.builders.Builders.builderEstoqueEntity;

class EstoqueMapperTest {

    @Test
    void testaSeRetornaUmEstoqueDomain() {
        Estoque estoque = EstoqueMapper.paraDomain(builderEstoqueEntity());
        Validators.validaEstoqueDomain(estoque);
    }

    @Test
    void testaSeRetornaUmEsttoqueEntity() {
        EstoqueEntity estoqueEntity = EstoqueMapper.paraEntity(builderEstoqueDomain());
        Validators.validaEstoqueEntity(estoqueEntity);
    }
}