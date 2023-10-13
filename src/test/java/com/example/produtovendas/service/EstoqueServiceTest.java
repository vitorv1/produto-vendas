package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataProvider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.builders.Builders.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @Mock
    private EstoqueDataProvider estoqueDataProvider;
    @Mock
    private ProdutoDataProvider produtoDataProvider;
    @InjectMocks
    private EstoqueService estoqueService;

    @Captor
    ArgumentCaptor<Estoque> captorEstoque;

    @Captor
    ArgumentCaptor<Produto> captorProduto;


    @Test
    void testeMetodoCriarEstoque() {
        Estoque estoque = builderEstoqueDomain();
        Mockito.when(estoqueDataProvider.salvar(captorEstoque.capture())).thenReturn(estoque);

        estoqueService.criarEstoque(builderProdutoDomain().get(0));

        Estoque estoqueTeste = captorEstoque.getValue();

        Validators.validaEstoqueDomain(estoqueTeste);
    }

    @Test
    void definirQuantidadeEstoqueDeProdutosDiferentes() {
        List<Produto> produtoList = builderProdutoDomain();
        Optional<Produto> produtoOptional = builderProdutoOptionalDomain().get(0);

        Mockito.when(produtoDataProvider.consultarPorId(any()))
                .thenReturn(produtoOptional)
                .thenAnswer(new Answer<Optional<Produto>>() {
                    int count = 0;
                    @Override
                    public Optional<Produto> answer(InvocationOnMock invocationOnMock){
                        count ++;
                        return builderProdutoOptionalDomain().get(count);
                     }
                });
        Mockito.when(produtoDataProvider.salvar(captorProduto.capture())).thenReturn(any());

        estoqueService.definirQuantidadeEstoque(produtoList);
    }
}