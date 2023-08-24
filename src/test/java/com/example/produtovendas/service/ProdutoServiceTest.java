package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Mock
    private ProdutoDataProvider produtoDataProvider;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.produtoService = new ProdutoService(produtoDataProvider);
    }

    @Test
    void testeMetodoCadastroProduto(){
        Long id1 = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "High";
        double valor2 = 300;

        Long id3 = 3L;
        String nome3 = "Colar";
        String marca3 = "Raiban";
        double valor3 = 400;

        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(new Produto(id1, nome1, inativo, marca1, valor1));
        produtoList.add(new Produto(id2, nome2, inativo, marca2, valor2));

        Produto produto = new Produto(id3, nome3, inativo, marca3, valor3);

        Mockito.when(produtoDataProvider.consultaTodos()).thenReturn(produtoList);
        Mockito.when(produtoDataProvider.salvar(any())).thenReturn(produto);

        Produto produtoTeste = produtoDataProvider.salvar(new Produto(null, nome3, inativo,marca3, valor3));

        Assertions.assertEquals(id3, produtoTeste.getId());
        Assertions.assertEquals(nome3, produtoTeste.getNome());
        Assertions.assertEquals(inativo, produtoTeste.isInativo());
        Assertions.assertEquals(marca3, produtoTeste.getMarca());
        Assertions.assertEquals(valor3, produtoTeste.getValor());
    }

    @Test
    void consultarProdutoPorId() {
    }

    @Test
    void consultaTodosProdutos() {
    }

    @Test
    void deletarProduto() {
    }

    @Test
    void alterarProduto() {
    }
}