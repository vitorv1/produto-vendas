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

import javax.swing.text.html.Option;
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
    ArgumentCaptor<Estoque> captor;


    @Test
    void testeMetodoCriarEstoque() {
        Estoque estoque = builderEstoqueDomain();
        Mockito.when(estoqueDataProvider.salvar(captor.capture())).thenReturn(estoque);

        estoqueService.criarEstoque(builderProdutoDomain().get(0));

        Estoque estoqueTeste = captor.getValue();

        Validators.validaEstoqueDomain(estoqueTeste);
    }

    @Test
    void definirQuantidadeEstoque() {
        List<Produto> produtoList = builderProdutoDomain();
        Optional<Produto> produtoOptional = Optional.of(builderProdutoDomain().get(0));

        Mockito.when(produtoDataProvider.consultarPorId(any()))
                .thenReturn(produtoOptional)
                .thenAnswer(new Answer<Produto>() {
                    int count = 0;
                    @Override
                    public Produto answer(InvocationOnMock invocationOnMock){
                        count ++;
                        return builderProdutoDomain().get(count);
                     }
                });
    }
}