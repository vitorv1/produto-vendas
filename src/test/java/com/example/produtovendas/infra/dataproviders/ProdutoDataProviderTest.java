package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.repositories.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;

class ProdutoDataProviderTest {

    @Autowired
    private ProdutoDataProvider produtoDataProvider;

    @Mock
    private ProdutoRepository repository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.produtoDataProvider = new ProdutoDataProvider(repository);
    }

    @Test
    void testeMetodoSalvar(){
        Long id = 1L;
        String nome = "Tenis";
        boolean inativo = false;
        String marca = "Nike";
        double valor = 250;

        Produto produto = new Produto(id, nome, inativo, marca, valor);

        Mockito.when(repository.save(any())).thenReturn(ProdutoMapper.paraEntity(produto));

        Produto produtoTeste = produtoDataProvider.salvar(produto);

        Assertions.assertEquals(id, produtoTeste.getId());
        Assertions.assertEquals(nome, produtoTeste.getNome());
        Assertions.assertEquals(inativo, produtoTeste.isInativo());
        Assertions.assertEquals(marca, produtoTeste.getMarca());
        Assertions.assertEquals(valor, produtoTeste.getValor());
    }

    @Test
    void testeMetodoConsultarPorId(){
        Long id = 1L;
        String nome = "Tenis";
        boolean inativo = false;
        String marca = "Nike";
        double valor = 250;

        Optional<ProdutoEntity> produtoEntity = Optional.of(new ProdutoEntity(id, nome, inativo, marca, valor));

        Mockito.when(repository.findById(any())).thenReturn(produtoEntity);

        Optional<Produto> produtoTeste = produtoDataProvider.consultarPorId(1L);

        produtoTeste.ifPresent(produto -> {
            Assertions.assertEquals(id, produtoTeste.get().getId());
            Assertions.assertEquals(nome, produtoTeste.get().getNome());
            Assertions.assertEquals(inativo, produtoTeste.get().isInativo());
            Assertions.assertEquals(marca, produtoTeste.get().getMarca());
            Assertions.assertEquals(valor, produtoTeste.get().getValor());
        });
    }

    @Test
    void testeMetodoConsultaTodos() {
        List<ProdutoEntity> produtoEntities = new ArrayList<>();

        Long id1 = 1L;
        String nome1 = "Tenis";
        boolean inativo = false;
        String marca1 = "Nike";
        double valor1 = 250;

        Long id2 = 2L;
        String nome2 = "Camiseta";
        String marca2 = "High";
        double valor2 = 300;

        produtoEntities.add(new ProdutoEntity(id1, nome1, inativo, marca1, valor1));
        produtoEntities.add(new ProdutoEntity(id2, nome2, inativo,marca2, valor2));

        Mockito.when(repository.findAll()).thenReturn(produtoEntities);

        List<Produto> produtoList = produtoDataProvider.consultaTodos();

        Produto produto1 = produtoList.get(0);
        Produto produto2 = produtoList.get(1);

        Assertions.assertEquals(id1, produto1.getId());
        Assertions.assertEquals(nome1, produto1.getNome());
        Assertions.assertEquals(inativo, produto1.isInativo());
        Assertions.assertEquals(marca1, produto1.getMarca());
        Assertions.assertEquals(valor1, produto1.getValor());
        Assertions.assertEquals(id2, produto2.getId());
        Assertions.assertEquals(nome2, produto2.getNome());
        Assertions.assertEquals(inativo, produto2.isInativo());
        Assertions.assertEquals(marca2, produto2.getMarca());
        Assertions.assertEquals(valor2, produto2.getValor());
    }

    @Test
    void testaSeMetodoSalvarProdutoEstaLancandoException(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> produtoDataProvider.salvar(
                new Produto(5L, "Tenis", false, "Nike", 350)));
        Assertions.assertEquals("Erro no cadastro do produto", exceptionTeste.getMessage());
    }

    @Test
    void testaSeMetodoConsultaPorIdEstaLancandoException(){
        Mockito.when(repository.findById(any())).thenThrow(RuntimeException.class);
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> produtoDataProvider.consultarPorId(3L));
        Assertions.assertEquals( "Erro na consalta por id",exceptionTeste.getMessage());
    }

    @Test
    void testaSeMetodoConsultaTodosEstaLancandoExceprion(){
        Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> produtoDataProvider.consultaTodos());
        Assertions.assertEquals("Erro na consulta por todos", exceptionTeste.getMessage());

    }
}