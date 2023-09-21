package com.example.produtovendas.service;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.service.ClienteService.MENSAGEM_CLIENTE_NAO_EXISTENTE;
import static com.example.produtovendas.validators.Validators.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteDataProvider clienteDataProvider;

    @Captor
    ArgumentCaptor<Cliente> captor;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testeMetodoCadastroCliente(){
        Cliente cliente = Builders.builderClienteDomain().get(0);

        when(clienteDataProvider.consultarPorCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(cliente);

        clienteService.cadastroCliente(cliente);

        Cliente clienteTeste = captor.getValue();

        validaClienteDomain(clienteTeste, 0);
    }

    @Test
    void testeMetodoConsultaTodosClientes(){
        Mockito.when(clienteDataProvider.consultarTodos()).thenReturn(Builders.builderClienteDomain());

        List<Cliente> clienteListTeste = assertDoesNotThrow(() -> clienteService.consultaTodosClientes());

        validaClienteDomain(clienteListTeste.get(0), 0);
        validaClienteDomain(clienteListTeste.get(1), 1);

        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarTodos();
    }

    @Test
    void testeMetodoConsultaClientePorId(){
        Long id = 1L;

        Optional<Cliente> cliente = Optional.of(Builders.builderClienteDomain().get(0));

        when(clienteDataProvider.consultarPorId(id)).thenReturn(cliente);

        Cliente clienteTeste = assertDoesNotThrow(() -> clienteService.consultaClienteExistentePorId(id));

        validaClienteDomain(clienteTeste, 0);

        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoConsultaClientePorIdNaoExistente(){
        Long id = 1L;
        when(clienteDataProvider.consultarPorId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.consultaClienteExistentePorId(id));

        Assertions.assertEquals(MENSAGEM_CLIENTE_NAO_EXISTENTE,exception.getMessage());
        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoDeletarCliente(){
        Long id = 1L;

        Optional<Cliente> clienteOptional = Optional.of(Builders.builderClienteDomain().get(0));

        when(clienteDataProvider.consultarPorId(any())).thenReturn(clienteOptional);
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(any());

        clienteService.deletarCliente(id);

        Cliente clienteTeste = captor.getValue();

        assertTrue(clienteTeste.isInativo());
    }

    @Test
    void testeMetodoAlterarCliente(){
        Long id = 1L;

        Cliente clienteDto = Builders.builderClienteDomain().get(1);
        clienteDto.setId(null);

        Optional<Cliente> clienteOptional = Optional.of(Builders.builderClienteDomain().get(0));
        when(clienteDataProvider.consultarPorId(any())).thenReturn(clienteOptional);
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(any());

        clienteService.alterarCliente(id, clienteDto);

        Cliente clienteTeste = captor.getValue();

        validaClienteDomain(clienteTeste, 1);
    }
}