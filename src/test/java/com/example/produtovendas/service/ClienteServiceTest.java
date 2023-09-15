package com.example.produtovendas.service;

<<<<<<< HEAD
import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.validators.Validators;
=======
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.validation.BindingResultUtils;
=======
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.service.ClienteService.MENSAGEM_CLIENTE_EXISTENTE;
<<<<<<< HEAD
import static com.example.produtovendas.validators.Validators.validaClienteDomain;
import static com.example.produtovendas.validators.Validators.validaProdutoEntity;
=======
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Mock
    private ClienteDataProvider clienteDataProvider;

    @Captor
    ArgumentCaptor<Cliente> captor;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.clienteService = new ClienteService(clienteDataProvider);
    }

    @Test
    void testeMetodoCadastroCliente(){
<<<<<<< HEAD
        Cliente cliente = Builders.builderClienteDomain().get(0);
=======
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(null , nome, inativo, cpf, email, numeroTelefone);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

        List<Cliente> clienteList = new ArrayList<>();

        clienteList.add(new Cliente(2L, "Daniel", false, "321654987-99", "francis@hotmail.com", "(44)99857-6969"));
        clienteList.add(new Cliente(3L, "Ana", false, "852963741-87", "rita@gmail.com", "(44)99326-8547"));
<<<<<<< HEAD
        when(clienteDataProvider.consultarTodos()).thenReturn(Builders.builderClienteDomain());
=======
        when(clienteDataProvider.consultarTodos()).thenReturn(clienteList);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(cliente);

        clienteService.cadastroCliente(cliente);

        Cliente clienteTeste = captor.getValue();

<<<<<<< HEAD
        validaClienteDomain(clienteTeste, null);
=======
        Assertions.assertNull(clienteTeste.getId());
        Assertions.assertEquals(clienteTeste.getNome(), nome);
        Assertions.assertEquals(clienteTeste.isInativo(), inativo);
        Assertions.assertEquals(clienteTeste.getCpf(), cpf);
        Assertions.assertEquals(clienteTeste.getEmail(), email);
        Assertions.assertEquals(clienteTeste.getNumeroTelefone(), numeroTelefone);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testeMetodoConsultaTodosClientes(){
<<<<<<< HEAD
        Mockito.when(clienteDataProvider.consultarTodos()).thenReturn(Builders.builderClienteDomain());

        List<Cliente> clienteListTeste = assertDoesNotThrow(() -> clienteService.consultaTodosClientes());

        Validators.validaClienteDomain(clienteListTeste.get(0), clienteListTeste.get(1));
=======
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Long id2 = 2L;
        String nome2 = "Daniel";
        String cpf2 = "789456123-11";
        String email2 = "francis@gmail.com";
        String numeroTelefone2 = "(44)99566-5823";

        Cliente cliente1 = new Cliente(id, nome, inativo, cpf, email, numeroTelefone);
        Cliente cliente2 = new Cliente(id2, nome2, inativo, cpf2, email2, numeroTelefone2);

        List<Cliente> clienteList = new ArrayList<>();

        clienteList.add(cliente1);
        clienteList.add(cliente2);

        Mockito.when(clienteDataProvider.consultarTodos()).thenReturn(clienteList);

        List<Cliente> clienteListTeste = assertDoesNotThrow(() -> clienteService.consultaTodosClientes());

        Assertions.assertEquals(clienteListTeste.get(0), cliente1);
        Assertions.assertEquals(clienteListTeste.get(1), cliente2);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarTodos();
    }

    @Test
    void testeMetodoConsultaClientePorId(){
        Long id = 1L;
<<<<<<< HEAD

        Optional<Cliente> cliente = Optional.of(Builders.builderClienteDomain().get(0));
=======
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "email@gmail.com";
        String numeroTelefone = "(44)99999-9999";

        Optional<Cliente> cliente = Optional.of(new Cliente(id, nome, inativo, cpf, email, numeroTelefone));
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

        when(clienteDataProvider.consultarPorId(id)).thenReturn(cliente);

        Cliente clienteTeste = assertDoesNotThrow(() -> clienteService.consultaClientePorId(id));

<<<<<<< HEAD
        validaClienteDomain(clienteTeste, null);

=======
        Assertions.assertEquals(cliente.get(), clienteTeste);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoConsultaClientePorIdNaoExistente(){
        Long id = 1L;
        when(clienteDataProvider.consultarPorId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.consultaClientePorId(id));

        Assertions.assertEquals(MENSAGEM_CLIENTE_EXISTENTE,exception.getMessage());
        Mockito.verify(clienteDataProvider, Mockito.times(1)).consultarPorId(id);
    }

    @Test
    void testeMetodoDeletarCliente(){
        Long id = 1L;
<<<<<<< HEAD

        Optional<Cliente> clienteOptional = Optional.of(Builders.builderClienteDomain().get(0));
=======
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Optional<Cliente> clienteOptional = Optional.of(new Cliente(id, nome, inativo, cpf, email, numeroTelefone));
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02

        when(clienteDataProvider.consultarPorId(any())).thenReturn(clienteOptional);
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(any());

        clienteService.deletarCliente(id);

        Cliente clienteTeste = captor.getValue();

        assertTrue(clienteTeste.isInativo());
    }

    @Test
<<<<<<< HEAD
    void testeMetodoAlterarCliente(){
        Long id = 1L;

        Cliente clienteDto = Builders.builderClienteDomain().get(1);
        clienteDto.setId(null);

        Optional<Cliente> clienteOptional = Optional.of(Builders.builderClienteDomain().get(0));
=======
    void alterarCliente(){
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Cliente clienteDto = new Cliente(null, nome, false, cpf, email, numeroTelefone);

        Optional<Cliente> clienteOptional = Optional.of(new Cliente(id, "Amanda", inativo,"789456123-99", "mandinha@gmail.com", "9987488-5689"));
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
        when(clienteDataProvider.consultarPorId(any())).thenReturn(clienteOptional);
        when(clienteDataProvider.salvar(captor.capture())).thenReturn(any());

        clienteService.alterarCliente(id, clienteDto);

        Cliente clienteTeste = captor.getValue();

<<<<<<< HEAD
        validaClienteDomain(clienteTeste, null);
=======
        Assertions.assertEquals(id, clienteTeste.getId());
        Assertions.assertEquals(clienteDto.getNome(), clienteTeste.getNome());
        Assertions.assertEquals(clienteDto.isInativo(), clienteTeste.isInativo());
        Assertions.assertEquals(clienteDto.getCpf(), clienteTeste.getCpf());
        Assertions.assertEquals(clienteDto.getEmail(), clienteTeste.getEmail());
        Assertions.assertEquals(clienteDto.getNumeroTelefone(), clienteTeste.getNumeroTelefone());
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }
}