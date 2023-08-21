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
    void testaSeEstaSalvandoNoBancoDeDados(){
        Long id = 4L;
        String nome = "João";
        boolean inativo = false;
        String cpf = "159753864-99";
        String email = "nunes@gmail.com";
        String numeroTelefone = "(44)99456-2322";
        Cliente cliente = new Cliente(id, nome, inativo, cpf, email, numeroTelefone);
        Mockito.when(repository.save(any())).thenReturn(ClienteMapper.paraEntity(cliente));
        Cliente clienteSalvo = clienteDataProvider.cadastrarCliente(cliente);
        Assertions.assertEquals(nome, clienteSalvo.getNome());
        Assertions.assertEquals(id, clienteSalvo.getId());
        Assertions.assertEquals(inativo, clienteSalvo.isInativo());
        Assertions.assertEquals(cpf, clienteSalvo.getCpf());
        Assertions.assertEquals(email, clienteSalvo.getEmail());
        Assertions.assertEquals(numeroTelefone, clienteSalvo.getNumeroTelefone());
    }

    @Test
    void testarSeEstaConsultandoTodosOsClientes(){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        Cliente cliente1 = new Cliente(1L, "Vitor", false, "123456789-99", "vivi@gmail.com", "(44)99874-8356");
        Cliente cliente2 = new Cliente(2L, "Daniel", false, "789456123-11", "francis@gmail.com", "(44)99874-5566");
        clienteEntities.add(ClienteMapper.paraEntity(cliente1));
        clienteEntities.add(ClienteMapper.paraEntity(cliente2));

        Mockito.when(repository.findAll()).thenReturn(clienteEntities);

        List<Cliente> clienteList = clienteDataProvider.consultarTodos();

        Assertions.assertEquals(cliente1.getNome(), clienteList.get(0).getNome());
        Assertions.assertEquals(cliente1.getId(), clienteList.get(0).getId());
        Assertions.assertEquals(cliente1.isInativo(), clienteList.get(0).isInativo());
        Assertions.assertEquals(cliente1.getCpf(), clienteList.get(0).getCpf());
        Assertions.assertEquals(cliente1.getEmail(), clienteList.get(0).getEmail());
        Assertions.assertEquals(cliente1.getNumeroTelefone(), clienteList.get(0).getNumeroTelefone());
        Assertions.assertEquals(cliente2.getNome(), clienteList.get(1).getNome());
        Assertions.assertEquals(cliente2.getId(), clienteList.get(1).getId());
        Assertions.assertEquals(cliente2.isInativo(), clienteList.get(1).isInativo());
        Assertions.assertEquals(cliente2.getCpf(), clienteList.get(1).getCpf());
        Assertions.assertEquals(cliente2.getEmail(), clienteList.get(1).getEmail());
        Assertions.assertEquals(cliente2.getNumeroTelefone(), clienteList.get(1).getNumeroTelefone());
    }

    @Test
    void testarSeEstaConsultandoPorId(){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        Cliente cliente1 = new Cliente(1L, "Vitor", false, "123456789-99", "vivi@gmail.com", "(44)99874-8356");
        Cliente cliente2 = new Cliente(2L, "Daniel", false, "789456123-11", "francis@gmail.com", "(44)99874-5566");
        clienteEntities.add(ClienteMapper.paraEntity(cliente1));
        clienteEntities.add(ClienteMapper.paraEntity(cliente2));
        Long id = cliente1.getId();

        Mockito.when(repository.findById(any())).thenReturn(clienteEntities.stream().filter(ClienteEntity -> id.equals(ClienteEntity.getId())).findFirst());

        Optional<Cliente> clienteConsultado = clienteDataProvider.consultarPorId(cliente1.getId());

        Assertions.assertEquals(cliente1.getId(), clienteConsultado.get().getId());
        Assertions.assertEquals(cliente1.getNome(), clienteConsultado.get().getNome());
        Assertions.assertEquals(cliente1.isInativo(), clienteConsultado.get().isInativo());
        Assertions.assertEquals(cliente1.getCpf(), clienteConsultado.get().getCpf());
        Assertions.assertEquals(cliente1.getEmail(), clienteConsultado.get().getEmail());
        Assertions.assertEquals(cliente1.getNumeroTelefone(), clienteConsultado.get().getNumeroTelefone());
    }

    @Test
    void testaSeEstaAlterandoCliente(){
        Long id = 1L;
        String nome = "João";
        String cpf = "159753864-99";
        String email = "nunes@gmail.com";
        String numeroTelefone = "(44)99456-2322";

        String nomeTeste = "Vitor";
        boolean inativo = false;
        String cpfTeste = "123456789-11";
        String emailTeste = "vivi@gmail.com";
        String numeroTelefoneTeste = "(44)99874-8356";
        Cliente clienteDto = new Cliente(1L, nome, false, cpf, email, numeroTelefone);
        Cliente cliente = new Cliente(id, nomeTeste, inativo, cpfTeste, emailTeste, numeroTelefoneTeste);
        Optional<ClienteEntity> clienteEntity = Optional.of(ClienteMapper.paraEntity(cliente));

        Mockito.when(repository.findById(any())).thenReturn(clienteEntity);
        Mockito.when(repository.save(any())).thenReturn(ClienteMapper.paraEntity(cliente));

        Cliente clienteTeste =  clienteDataProvider.aterarCliente(id, clienteDto);

        Assertions.assertEquals(id, clienteTeste.getId());
        Assertions.assertEquals(nomeTeste, clienteTeste.getNome());
        Assertions.assertEquals(inativo, clienteTeste.isInativo());
        Assertions.assertEquals(cpfTeste, clienteTeste.getCpf());
        Assertions.assertEquals(emailTeste, clienteTeste.getEmail());
        Assertions.assertEquals(numeroTelefoneTeste, clienteTeste.getNumeroTelefone());
    }

    @Test
    void testaSeEstaDeletandoCliente(){
        Long id = 1L;
        String nome = "João";
        boolean inativo = false;
        String cpf = "159753864-99";
        String email = "nunes@gmail.com";
        String numeroTelefone = "(44)99456-2322";

        Cliente cliente = new Cliente(id, nome, inativo, cpf, email, numeroTelefone);
        Optional<ClienteEntity> clienteEntity = Optional.of(ClienteMapper.paraEntity(cliente));

        Mockito.when(repository.findById(any())).thenReturn(clienteEntity);
        Mockito.when(repository.save(any())).thenReturn(cliente);

        clienteDataProvider.deletarCliente(id);

        Assertions.assertTrue(cliente.isInativo());

    }

    @Test
    void testaSeMetodoSalvarEstaLancandoExecption(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);

        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> clienteDataProvider.cadastrarCliente(
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