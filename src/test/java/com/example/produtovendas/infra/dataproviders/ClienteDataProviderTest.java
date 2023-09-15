package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.validators.Validators.validaClienteDomain;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ClienteDataProviderTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteDataProvider clienteDataProvider;

    @Test
    void testeMetodoSalvar() {
        Cliente cliente = Builders.builderClienteDomain().get(0);
        Mockito.when(repository.save(any())).thenReturn(ClienteMapper.paraEntity(cliente));
        Cliente clienteTeste = clienteDataProvider.salvar(cliente);
        validaClienteDomain(clienteTeste, null);
    }

    @Test
    void testeMetodoConsultarTodos(){
        List<ClienteEntity> clienteEntities = Builders.builderClienteEntity();

        Mockito.when(repository.findAll()).thenReturn(clienteEntities);
        List<Cliente> clientes = clienteDataProvider.consultarTodos();

        validaClienteDomain(clientes.get(0), clientes.get(1));
    }

    @Test
    void testeMetodoConsultarPorId(){

        Optional<ClienteEntity> clienteEntity = Builders.builderClienteOptional().get(0);
        Mockito.when(repository.findById(any())).thenReturn(clienteEntity);

        Optional<Cliente> clienteTeste = clienteDataProvider.consultarPorId(2L);

        clienteTeste.ifPresent(cliente -> validaClienteDomain(clienteTeste.get(), null));
    }

    @Test
    void testaSeMetodoSalvarEstaLancandoExecption(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);

        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> clienteDataProvider.salvar(Builders.builderClienteDomain().get(0)));
        Assertions.assertEquals("Erro ao salvar Cliente", exceptionTeste.getMessage());

    }

    @Test
    void testaSeMetodoConsultarTodosEstaLancandoException(){
        Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);

        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> clienteDataProvider.consultarTodos());
        Assertions.assertEquals("Erro ao buscar todos os Clientes", exceptionTeste.getMessage());
    }

    @Test
    void testaSeMetodoConsultarClientePorIdEstaLancandoException(){
        Mockito.when(repository.findById(any())).thenThrow(RuntimeException.class);

        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> clienteDataProvider.consultarPorId(1L));
        Assertions.assertEquals("Erro ao consultar Cliente por Id.", exceptionTeste.getMessage());
    }
}