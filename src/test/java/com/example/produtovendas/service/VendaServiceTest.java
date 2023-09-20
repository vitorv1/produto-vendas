package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.builders.Builders.*;
import static com.example.produtovendas.service.VendaService.MENSAGEM_VENDA_EXISTE;
import static com.example.produtovendas.validators.Validators.*;
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
        Venda venda = builderVendaDomainDto(0);

        Mockito.when(produtoService.consultarProdutoPorId(any()))
                .thenReturn(builderProdutoDomain().get(0))
                .thenAnswer(new Answer<Produto>() {
                    int count = 0;
                    @Override
                    public Produto answer(InvocationOnMock invocation){
                        count++;
                        return builderProdutoDomain().get(count);
                    }
                });
        Mockito.when(clienteService.consultaClientePorId(any())).thenReturn(builderClienteDomain().get(0));
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(venda);

        vendaService.cadastroVenda(venda);

        Venda vendaTeste = captor.getValue();

        validaVendaDomain(vendaTeste, 0);
    }

    @Test
    void testeMetodoBuscarVendaPorId(){
        Long idVenda = 2L;

        Optional<Venda> vendaOptional = Optional.of(builderVendaDomain().get(0));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);

        Venda vendaTeste = assertDoesNotThrow(() -> vendaService.buscarPorId(idVenda));

        validaVendaDomain(vendaTeste, 0);

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

        List<Venda> vendaList  = builderVendaDomain();

        Mockito.when(vendaDataProvider.buscarTodos()).thenReturn(vendaList);

        List<Venda> vendasTeste = assertDoesNotThrow(()-> vendaService.buscarTodos());

        validaVendaDomain(vendasTeste.get(0), 0);
        validaVendaDomain(vendasTeste.get(1), 1);

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarTodos();
    }

    @Test
    void testeMetodoDeletarVenda() {
        Long idVenda = 2L;

        Optional<Venda> vendaOptional = Optional.of(builderVendaDomain().get(0));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(any());

        vendaService.deletarVenda(idVenda);

        Venda vendaTeste = captor.getValue();

        Assertions.assertTrue(vendaTeste.isInativo());
    }

    @Test
    void testeMetodoAlterarVenda(){
        Long idVenda = 2L;
        Venda vendaDto = builderVendaDomainDto(1);

        Optional<Venda> vendaOptional = Optional.of(builderVendaDomain().get(0));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);
        Mockito.when(clienteService.consultaClientePorId(any())).thenReturn(builderClienteDomain().get(1));
        Mockito.when(produtoService.consultarProdutoPorId(any())).thenReturn(builderProdutoDomain().get(0));
        Mockito.when(vendaDataProvider.salvar(captor.capture())).thenReturn(any());

        vendaService.alterarVenda(idVenda, vendaDto);

        Venda vendaTeste = captor.getValue();

        validaVendaDomain(vendaTeste, 1);
    }

    @Test
    void testeMetodoCadastroEstaLancandoExceptionDeClienteInativo(){
        Venda venda = builderVendaDomainDto(0);
        Cliente cliente = builderClienteDomain().get(0);
        cliente.setInativo(true);
        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(any());
        Mockito.when(clienteService.consultaClientePorId(any())).thenReturn(cliente);
        Mockito.when(produtoService.consultarProdutoPorId(any())).thenReturn(any());

        Assertions.assertThrows(RuntimeException.class, () -> vendaService.cadastroVenda(venda));
    }
}