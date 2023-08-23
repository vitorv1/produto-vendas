package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.repositories.ClienteRepository;
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

import static org.mockito.ArgumentMatchers.any;

class ClienteDataProviderTest {

    @Autowired
    private ClienteDataProvider clienteDataProvider;

    @Mock
    private ClienteRepository repository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.clienteDataProvider = new ClienteDataProvider(repository);
    }

    @Test
    void testeMetodoSalvar(){
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-77";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99566-8523";

        Cliente cliente = new Cliente(id, nome, inativo, cpf, email, numeroTelefone);
        Mockito.when(repository.save(any())).thenReturn(ClienteMapper.paraEntity(cliente));
        Cliente clienteTeste = clienteDataProvider.salvar(cliente);

        Assertions.assertEquals(id, clienteTeste.getId());
        Assertions.assertEquals(nome, clienteTeste.getNome());
        Assertions.assertEquals(inativo, clienteTeste.isInativo());
        Assertions.assertEquals(cpf, clienteTeste.getCpf());
        Assertions.assertEquals(email, clienteTeste.getEmail());
        Assertions.assertEquals(numeroTelefone, clienteTeste.getNumeroTelefone());
    }

    @Test
    void testeMetodoConsultarTodos(){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        Long id1 = 1L;
        String nome1 = "Vitor";
        boolean inativo = false;
        String cpf1 = "123456789-77";
        String email1 = "vivi@gmail.com";
        String numeroTelefone1 = "(44)99566-8523";

        Long id2 = 2L;
        String nome2 = "Daniel";
        String cpf2 = "789456123-11";
        String email2 = "francis@gmail.com";
        String numeroTelefone2 = "(44)99566-5823";

        ClienteEntity clienteEntity1 = new ClienteEntity(id1, nome1, inativo, cpf1, email1, numeroTelefone1);
        ClienteEntity clienteEntity2 = new ClienteEntity(id2, nome2, inativo, cpf2, email2, numeroTelefone2);

        clienteEntities.add(clienteEntity1);
        clienteEntities.add(clienteEntity2);

        Mockito.when(repository.findAll()).thenReturn(clienteEntities);

        List<Cliente> clientes = clienteDataProvider.consultarTodos();

        Cliente cliente1 = clientes.get(0);
        Cliente cliente2 = clientes.get(1);

        Assertions.assertEquals(id1, cliente1.getId());
        Assertions.assertEquals(nome1, cliente1.getNome());
        Assertions.assertEquals(inativo, cliente1.isInativo());
        Assertions.assertEquals(cpf1, cliente1.getCpf());
        Assertions.assertEquals(email1, cliente1.getEmail());
        Assertions.assertEquals(numeroTelefone1, cliente1.getNumeroTelefone());
        Assertions.assertEquals(id2, cliente2.getId());
        Assertions.assertEquals(nome2, cliente2.getNome());
        Assertions.assertEquals(inativo, cliente2.isInativo());
        Assertions.assertEquals(cpf2, cliente2.getCpf());
        Assertions.assertEquals(email2, cliente2.getEmail());
        Assertions.assertEquals(numeroTelefone2, cliente2.getNumeroTelefone());
    }

    @Test
    void testeMetodoConsultarPorId(){
        Long id = 2L;
        String nome = "Daniel";
        boolean inativo = false;
        String cpf = "789456123-11";
        String email = "francis@gmail.com";
        String numeroTelefone = "(44)99566-5823";

        Optional<ClienteEntity> clienteEntity = Optional.of(new ClienteEntity(id, nome, inativo, cpf, email, numeroTelefone));

        Mockito.when(repository.findById(any())).thenReturn(clienteEntity);

        Optional<Cliente> clienteTeste = clienteDataProvider.consultarPorId(2L);

        clienteTeste.ifPresent(cliente -> {
            Assertions.assertEquals(id, clienteTeste.get().getId());
            Assertions.assertEquals(nome, clienteTeste.get().getNome());
            Assertions.assertEquals(inativo, clienteTeste.get().isInativo());
            Assertions.assertEquals(cpf, clienteTeste.get().getCpf());
            Assertions.assertEquals(email, clienteTeste.get().getEmail());
            Assertions.assertEquals(numeroTelefone, clienteTeste.get().getNumeroTelefone());
        });

    }
    @Test
    void testaSeMetodoSalvarEstaLancandoExecption(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);

        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> clienteDataProvider.salvar(
                new Cliente(1L, "Vitor", false, "123456789-99", "vivi@gmail.com", "(44)99874-8356")));
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