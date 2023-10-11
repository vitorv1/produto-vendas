package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.infra.mappers.EstoqueMapper;
import com.example.produtovendas.infra.repositories.EstoqueRepository;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.produtovendas.builders.Builders.builderEstoqueDomain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EstoqueDataProviderTest {

    @Mock
    private EstoqueRepository repository;

    @InjectMocks
    private EstoqueDataProvider estoqueDataProvider;

    @Test
    void testeMetodoSalvar() {
        Estoque estoque = builderEstoqueDomain();
        Mockito.when(repository.save(any())).thenReturn(EstoqueMapper.paraEntity(estoque));
        Estoque estoqueTeste = estoqueDataProvider.salvar(estoque);
        Validators.validaEstoqueDomain(estoqueTeste);
    }
}