package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.service.VendaService.MENSAGEM_VENDA_EXISTE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class VendaServiceTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private VendaDataProvider vendaDataProvider;

    @Captor
    private ArgumentCaptor<Venda> captor;

    @Autowired
    private  VendaService vendaService;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.vendaService = new VendaService(clienteService, produtoService,vendaDataProvider);
    }

    @Test
    void testeMetodoCadastroVenda() {
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(id , nome, inativo, cpf, email, numeroTelefone);

        Long id1 = 1L;
        String nome1 = "Tenis";
        String marca1 = "Nike";
        double valor1 = 250;

        Produto produto1 = new Produto(id1, nome1, inativo, marca1, valor1);

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(1L, null, false, null, 0));
        produtos.add(new Produto(2L, null, false, null, 0));

        Venda venda = new Venda(null, null, id, 0, false, 10, produtos, null);

        Mockito.when(produtoService.consultarProdutoPorId(any())).thenReturn(produto1);
        Mockito.when(clienteService.consultaClientePorId(any())).thenReturn(cliente);
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(venda);

        vendaService.cadastroVenda(venda);

        Venda vendaTeste = captor.getValue();

        Assertions.assertNull(vendaTeste.getId());
        Assertions.assertEquals(cliente, vendaTeste.getCliente());
        Assertions.assertEquals(id, vendaTeste.getIdCliente());
        Assertions.assertEquals(450, vendaTeste.getValor());
        Assertions.assertFalse(vendaTeste.isInativo());
        Assertions.assertEquals(10, vendaTeste.getDesconto());
        Assertions.assertEquals(produto1, vendaTeste.getListaProdutos().get(0));
        Assertions.assertEquals(produto1, vendaTeste.getListaProdutos().get(1));
        Assertions.assertEquals(LocalDate.now(), vendaTeste.getDataVenda());
    }

    @Test
    void testeMetodoBuscarVendaPorId(){
        Long idVenda = 2L;
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(id , nome, inativo, cpf, email, numeroTelefone);

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(produto1);
        produtoList.add(produto2);

        Optional<Venda> vendaOptional = Optional.of(new Venda(idVenda, cliente, id, 0, inativo, 0, produtoList, LocalDate.now()));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);

        Venda vendaTeste = assertDoesNotThrow(() -> vendaService.buscarPorId(idVenda));

        Assertions.assertEquals(vendaOptional.get(), vendaTeste);

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarPorId(idVenda);
    }

    @Test
    void testeMetodoBuscarPorIdVendaNaoExiste(){
        Long idVenda = 1L;
        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(Optional.empty());

        RuntimeException exceptionTeste = assertThrows(RuntimeException.class,() -> vendaService.buscarPorId(idVenda));

        Assertions.assertEquals(MENSAGEM_VENDA_EXISTE, exceptionTeste.getMessage());

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarPorId(idVenda);
    }

    @Test
    void testeMetodoBuscarTodos() {
        Long idVenda1 = 2L;
        Long idVenda2 = 3L;
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(id , nome, inativo, cpf, email, numeroTelefone);

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(produto1);
        produtoList.add(produto2);

        Venda venda1 = new Venda(idVenda1, cliente, id, 0, inativo, 0, produtoList, LocalDate.now());
        Venda venda2 = new Venda(idVenda2, cliente, id, 0, inativo, 0, produtoList, LocalDate.now());

        List<Venda> vendaList  = new ArrayList<>();

        vendaList.add(venda1);
        vendaList.add(venda2);

        Mockito.when(vendaDataProvider.buscarTodos()).thenReturn(vendaList);

        List<Venda> vendasTeste = assertDoesNotThrow(()-> vendaService.buscarTodos());

        Assertions.assertEquals(vendasTeste.get(0), venda1);
        Assertions.assertEquals(vendasTeste.get(1), venda2);

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarTodos();
    }

    @Test
    void testeMetodoDeletarVenda() {
        Long idVenda = 2L;
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(id , nome, inativo, cpf, email, numeroTelefone);

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(produto1);
        produtoList.add(produto2);

        Optional<Venda> vendaOptional = Optional.of(new Venda(idVenda, cliente, id, 0, inativo, 0, produtoList, LocalDate.now()));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(any());

        vendaService.deletarVenda(idVenda);

        Venda vendaTeste = captor.getValue();

        Assertions.assertTrue(vendaTeste.isInativo());
    }

    @Test
    void testeMetodoAlterarVenda(){
        Long idVenda = 2L;
        Long id = 1L;
        Long idDto = 2L;

        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Cliente cliente = new Cliente(id , nome, inativo, cpf, email, numeroTelefone);

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Long idClienteVenda = 5L;
        String nomeCliente = "Gustavo";
        String cpfCliete = "12345678-44";
        String emailCliente = "gusta@gmail.com";
        String numeroTelefoneCliente = "(44)99847-8356";

        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<Produto> produtoList = new ArrayList<>();

        produtoList.add(produto1);
        produtoList.add(produto2);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, null, false, null, 0));
        produtos.add(new Produto(2L, null, false, null, 0));

        Venda vendaDto = new Venda(null, null, idDto, 0, false, 10, produtos, null);

        Cliente clienteVenda = new Cliente(idDto, nomeCliente, inativo, cpfCliete, emailCliente, numeroTelefoneCliente);



        Optional<Venda> vendaOptional = Optional.of(new Venda(idVenda, cliente, id, 0, inativo, 0, produtoList, LocalDate.now()));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);
        Mockito.when(clienteService.consultaClientePorId(any())).thenReturn(clienteVenda);
        Mockito.when(produtoService.consultarProdutoPorId(any())).thenReturn(produto1);
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(any());

        vendaService.alterarVenda(idVenda, vendaDto);

        Venda vendaTeste = captor.getValue();

        Assertions.assertEquals(idVenda, vendaTeste.getId());
        Assertions.assertEquals(clienteVenda, vendaTeste.getCliente());
        Assertions.assertEquals(540, vendaTeste.getValor());
        Assertions.assertFalse(vendaTeste.isInativo());
        Assertions.assertEquals(10, vendaTeste.getDesconto());
        Assertions.assertEquals(produto1, vendaTeste.getListaProdutos().get(0));
        Assertions.assertEquals(produto1, vendaTeste.getListaProdutos().get(1));
        Assertions.assertEquals(LocalDate.now(), vendaTeste.getDataVenda());
    }
}