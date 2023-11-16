package com.example.produtovendas.service;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataProvider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.service.ProdutoService.MENSAGEM_PRODUTO_EXISTENTE;
//import static com.example.produtovendas.validators.Validators.validaProdutoDomain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

   /* @Mock
    private ProdutoDataProvider produtoDataProvider;

    @Mock
    private EstoqueService estoqueService;

    @Captor
    private ArgumentCaptor<Produto> captor;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void testeMetodoCadastroProduto(){
        Produto produto = Builders.builderProdutoDomain().get(0);

        when(produtoDataProvider.consultaTodos()).thenReturn(Collections.emptyList());
        when(produtoDataProvider.salvar(captor.capture())).thenReturn(produto);

        produtoService.cadastroProduto(produto);

        Mockito.verify(estoqueService, Mockito.times(1)).criarEstoque(any());

        Produto produtoTeste = captor.getValue();

        validaProdutoDomain(produtoTeste, 0);
    }

    @Test
    void testeMetodoConsultarProdutoPorId() {
        Long id1 = 1L;

        Optional<Produto> produtoOptional = Optional.of(builderProdutoDomain().get(0));

        when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);

        Produto produtoTeste = assertDoesNotThrow(() -> produtoService.consultarProdutoExistentePorId(id1));

        validaProdutoDomain(produtoTeste, 0);

        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultarPorId(id1);
    }

    @Test
    void testeMetodoConsultarProdutoPorIdNaoExiste(){
        Long id = 1L;
        when(produtoDataProvider.consultarPorId(any())).thenReturn(Optional.empty());

        RuntimeException exceptionTeste = assertThrows(RuntimeException.class, () -> produtoService.consultarProdutoExistentePorId(id));

        Assertions.assertEquals(MENSAGEM_PRODUTO_EXISTENTE, exceptionTeste.getMessage());
        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoConsultaTodosProdutos() {
        Mockito.when(produtoDataProvider.consultaTodos()).thenReturn(builderProdutoDomain());

        List<Produto> produtosTeste = assertDoesNotThrow(()-> produtoService.consultaTodosProdutos());

        validaProdutoDomain(produtosTeste.get(0), 0);
        validaProdutoDomain(produtosTeste.get(1), 1);

        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultaTodos();
    }

    @Test
    void testeMetodoDeletarProduto() {
        Long id = 1L;

        Optional<Produto> produtoOptional = Optional.of(builderProdutoDomain().get(0));
        Mockito.when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);
        when(produtoDataProvider.salvar(captor.capture())).thenReturn(any());

        produtoService.deletarProduto(id);

        Produto produto = captor.getValue();

        Assertions.assertTrue(produto.isInativo());
    }

    @Test
    void testaMetodoAlterarProduto() {
        Long id = 1L;

        Produto produtoDto = builderProdutoDomain().get(1);

        Optional<Produto> produtoOptional = Optional.of(builderProdutoDomain().get(0));

        Mockito.when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);
        Mockito.when(produtoDataProvider.salvar(captor.capture())).thenReturn(any());

        produtoService.alterarProduto(id, produtoDto);

        Produto produtoTeste = captor.getValue();

        validaProdutoDomain(produtoTeste, 1);
    }*/
}