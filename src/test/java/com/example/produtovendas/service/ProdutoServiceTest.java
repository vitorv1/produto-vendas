package com.example.produtovendas.service;

<<<<<<< HEAD
import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import jakarta.validation.Valid;
=======
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.service.ProdutoService.MENSAGEM_PRODUTO_EXISTENTE;
<<<<<<< HEAD
import static com.example.produtovendas.validators.Validators.validaProdutoDomain;
=======
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Mock
    private ProdutoDataProvider produtoDataProvider;

    @Captor
    private ArgumentCaptor<Produto> captor;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.produtoService = new ProdutoService(produtoDataProvider);
    }

    @Test
    void testeMetodoCadastroProduto(){
<<<<<<< HEAD
=======
        Long id1 = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "High";
        double valor2 = 300;

>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
        Long id3 = 3L;
        String nome3 = "Colar";
        String marca3 = "Raiban";
        double valor3 = 400;

<<<<<<< HEAD
        Produto produto = new Produto(id3, nome3, false, marca3, valor3);

        when(produtoDataProvider.consultaTodos()).thenReturn(Builders.builderProdutoDomain());
        when(produtoDataProvider.salvar(captor.capture())).thenReturn(any());
=======
        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(new Produto(id1, nome1, inativo, marca1, valor1));
        produtoList.add(new Produto(id2, nome2, inativo, marca2, valor2));

        Produto produto = new Produto(id3, nome3, inativo, marca3, valor3);

        when(produtoDataProvider.consultaTodos()).thenReturn(produtoList);
        when(produtoDataProvider.salvar(captor.capture())).thenReturn(produto);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

        produtoService.cadastroProduto(produto);

        Produto produtoTeste = captor.getValue();

<<<<<<< HEAD
        validaProdutoDomain(produtoTeste, null);
=======
        Assertions.assertEquals(produtoTeste, produto);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testeMetodoConsultarProdutoPorId() {
        Long id1 = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Optional<Produto> produtoOptional = Optional.of(new Produto(id1, nome1, inativo, marca1, valor1));

        when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);

        Produto produtoTeste = assertDoesNotThrow(() -> produtoService.consultarProdutoPorId(id1));

        Assertions.assertEquals(produtoOptional.get(), produtoTeste);

        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultarPorId(id1);
    }

    @Test
    void testeMetodoConsultarProdutoPorIdNaoExiste(){
        Long id = 1L;
        when(produtoDataProvider.consultarPorId(any())).thenReturn(Optional.empty());

        RuntimeException exceptionTeste = assertThrows(RuntimeException.class, () -> produtoService.consultarProdutoPorId(id));

        Assertions.assertEquals(MENSAGEM_PRODUTO_EXISTENTE, exceptionTeste.getMessage());
        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoConsultaTodosProdutos() {
        Long id1 = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "High";
        double valor2 = 300;

        List<Produto> produtoList = new ArrayList<>();

        Produto produto1 = new Produto(id1, nome1, inativo, marca1, valor1);
        Produto produto2 = new Produto(id2, nome2, inativo, marca2, valor2);
        produtoList.add(produto1);
        produtoList.add(produto2);

        Mockito.when(produtoDataProvider.consultaTodos()).thenReturn(produtoList);

        List<Produto> produtosTeste = assertDoesNotThrow(()-> produtoService.consultaTodosProdutos());

        Assertions.assertEquals(produtosTeste.get(0), produto1);
        Assertions.assertEquals(produtosTeste.get(1), produto2);

        Mockito.verify(produtoDataProvider, Mockito.times(1)).consultaTodos();
    }

    @Test
    void testeMetodoDeletarProduto() {
        Long id = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Optional<Produto> produtoOptional = Optional.of(new Produto(id, nome1, inativo, marca1, valor1));
        Mockito.when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);
        when(produtoDataProvider.salvar(captor.capture())).thenReturn(any());

        produtoService.deletarProduto(id);

        Produto produto = captor.getValue();

        Assertions.assertTrue(produto.isInativo());
    }

    @Test
    void testaMetodoAlterarProduto() {
        Long id = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        String nome2 = "Camiseta";
        String marca2 = "High";
        double valor2 = 300;

        Produto produtoDto = new Produto(null, nome2, inativo, marca2, valor2);

        Optional<Produto> produtoOptional = Optional.of(new Produto(id, nome1, inativo, marca1, valor1));

        Mockito.when(produtoDataProvider.consultarPorId(any())).thenReturn(produtoOptional);
        Mockito.when(produtoDataProvider.salvar(captor.capture())).thenReturn(any());

        produtoService.alterarProduto(id, produtoDto);

        Produto produtoTeste = captor.getValue();

        Assertions.assertEquals(produtoTeste.getId(), id);
        Assertions.assertEquals(produtoTeste.getNome(), nome2);
        Assertions.assertFalse(produtoTeste.isInativo());
        Assertions.assertEquals(produtoTeste.getMarca(), marca2);
        Assertions.assertEquals(produtoTeste.getValor(), valor2);
    }
}