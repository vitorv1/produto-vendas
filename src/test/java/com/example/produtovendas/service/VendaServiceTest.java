package com.example.produtovendas.service;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.builders.Builders.*;
import static com.example.produtovendas.service.VendaService.MENSAGEM_VENDA_EXISTE;
import static com.example.produtovendas.validators.Validators.validaVendaDomain;
import static com.example.produtovendas.validators.Validators.validaVendaDto;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private VendaDataProvider vendaDataProvider;


    @Captor
    private ArgumentCaptor<Venda> captorVenda;

    @InjectMocks
    private  VendaService vendaService;

    @Test
    void testeMetodoCadastroVenda() {
        Venda venda = builderVendaDomain().get(0);
        VendaDto vendaDto = builderVendaDto().get(0);

        Mockito.when(produtoService.consultarProdutoExistentePorId(any()))
                .thenReturn(builderProdutoDomain().get(0))
                .thenAnswer(new Answer<Produto>() {
                    int count = 0;
                    @Override
                    public Produto answer(InvocationOnMock invocation){
                        count++;
                        return builderProdutoDomain().get(count);
                    }
                });
        Mockito.when(clienteService.consultaClienteExistentePorId(any())).thenReturn(builderClienteDomain().get(0));
        Mockito.when(vendaDataProvider.salvar(captorVenda.capture())).thenReturn(venda);
        List<Produto> produtoList = builderProdutoDomain();
        produtoList.forEach(produto -> produto.setQuantidade(produto.getQuantidade() - 1));
        Mockito.when(estoqueService.definirQuantidadeEstoque(any())).thenReturn(produtoList);


        vendaService.cadastroVenda(vendaDto);

        Venda vendaTeste = captorVenda.getValue();

        validaVendaDomain(vendaTeste, 0);
    }

    @Test
    void testeMetodoBuscarVendaPorId(){
        Long idVenda = 2L;

        Optional<Venda> vendaOptional = Optional.of(builderVendaDomain().get(0));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);

        VendaDto vendaTeste = assertDoesNotThrow(() -> vendaService.buscarPorId(idVenda));

        validaVendaDto(vendaTeste, 0);

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarPorId(idVenda);
    }

    @Test
    void testeMetodoBuscarPorIdVendaNaoExiste(){
        Long idVenda = 1L;
        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(Optional.empty());

        RuntimeException exceptionTeste = assertThrows(RuntimeException.class,() -> vendaService.buscarExistentePorId(idVenda));

        Assertions.assertEquals(MENSAGEM_VENDA_EXISTE, exceptionTeste.getMessage());

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarPorId(idVenda);
    }

    @Test
    void testeMetodoBuscarTodos() {

        List<Venda> vendaList  = builderVendaDomain();

        Mockito.when(vendaDataProvider.buscarTodos()).thenReturn(vendaList);

        List<VendaDto> vendasTeste = assertDoesNotThrow(()-> vendaService.buscarTodos());

        validaVendaDto(vendasTeste.get(0), 0);
        validaVendaDto(vendasTeste.get(1), 1);

        Mockito.verify(vendaDataProvider, Mockito.times(1)).buscarTodos();
    }

    @Test
    void testeMetodoDeletarVenda() {
        Mockito.doNothing().when(vendaDataProvider).deletar(any());
        vendaService.deletarVenda(1L);
        Mockito.verify(vendaDataProvider, Mockito.times(1)).deletar(any());
    }

    @Test
    void testeMetodoAlterarVenda(){
        Long idVenda = 2L;
        VendaDto vendaDto = builderVendaDto().get(0);

        Optional<Venda> vendaOptional = Optional.of(builderVendaDomain().get(1));

        Mockito.when(vendaDataProvider.buscarPorId(any())).thenReturn(vendaOptional);
        Mockito.when(clienteService.consultaClienteExistentePorId(any())).thenReturn(builderClienteDomain().get(0));
        Mockito.when(vendaDataProvider.salvar(captorVenda.capture())).thenReturn(Builders.builderVendaDomain().get(0));

        vendaService.alterarVenda(idVenda, vendaDto);

        Venda vendaTeste = captorVenda.getValue();

        validaVendaDomain(vendaTeste, 0);
    }

    @Test
    void testeMetodoCadastroEstaLancandoExceptionDeClienteInativo(){
        VendaDto vendaDto = builderVendaDto().get(0);
        Cliente cliente = builderClienteDomain().get(0);
        cliente.setInativo(true);
        Mockito.when(clienteService.consultaClienteExistentePorId(any())).thenReturn(cliente);

        Assertions.assertThrows(RuntimeException.class, () -> vendaService.cadastroVenda(vendaDto));
    }
}