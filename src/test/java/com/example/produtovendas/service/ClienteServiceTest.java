package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Mock
    private ClienteDataProvider clienteDataProvider;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.clienteService = new ClienteService(clienteDataProvider);
    }
    @Test
    void testeMetodoCadastroCliente(){
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";
        Cliente cliente = new Cliente(id, nome, inativo, cpf, email, numeroTelefone);

        List<Cliente> clienteList = new ArrayList<>();

        clienteList.add(new Cliente(2L, "Daniel", false, "321654987-99", "francis@hotmail.com", "(44)99857-6969"));
        clienteList.add(new Cliente(3L, "Ana", false, "852963741-87", "rita@gmail.com", "(44)99326-8547"));
        Mockito.when(clienteDataProvider.consultarTodos()).thenReturn(clienteList);
        Mockito.when(clienteDataProvider.salvar(any())).thenReturn(cliente);

        Cliente clienteTeste = clienteService.cadastroCliente(new Cliente(null, nome, inativo, cpf, email, numeroTelefone));

        Assertions.assertEquals(clienteTeste.getId(), id);
        Assertions.assertEquals(clienteTeste.getNome(), nome);
        Assertions.assertEquals(clienteTeste.isInativo(), inativo);
        Assertions.assertEquals(clienteTeste.getCpf(), cpf);
        Assertions.assertEquals(clienteTeste.getEmail(), email);
        Assertions.assertEquals(clienteTeste.getNumeroTelefone(), numeroTelefone);
    }

    @Test
    void testeMetodoConsultaTodosClientes(){
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

        List<Cliente> clienteListTeste = clienteService.consultaTodosClientes();

        Assertions.assertEquals(clienteList.get(0).getId(), clienteListTeste.get(0).getId());
        Assertions.assertEquals(clienteList.get(0).getNome(), clienteListTeste.get(0).getNome());
        Assertions.assertEquals(clienteList.get(0).isInativo(), clienteListTeste.get(0).isInativo());
        Assertions.assertEquals(clienteList.get(0).getCpf(), clienteListTeste.get(0).getCpf());
        Assertions.assertEquals(clienteList.get(0).getEmail(), clienteListTeste.get(0).getEmail());
        Assertions.assertEquals(clienteList.get(0).getNumeroTelefone(), clienteListTeste.get(0).getNumeroTelefone());
        Assertions.assertEquals(clienteList.get(1).getId(), clienteListTeste.get(1).getId());
        Assertions.assertEquals(clienteList.get(1).getNome(), clienteListTeste.get(1).getNome());
        Assertions.assertEquals(clienteList.get(1).isInativo(), clienteListTeste.get(1).isInativo());
        Assertions.assertEquals(clienteList.get(1).getCpf(), clienteListTeste.get(1).getCpf());
        Assertions.assertEquals(clienteList.get(1).getEmail(), clienteListTeste.get(1).getEmail());
        Assertions.assertEquals(clienteList.get(1).getNumeroTelefone(), clienteListTeste.get(1).getNumeroTelefone());
    }

    @Test
    void testeMetodoConsultaClientePorId(){
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Optional<Cliente> cliente = Optional.of(new Cliente(id, nome, inativo, cpf, email, numeroTelefone));

        Mockito.when(clienteDataProvider.consultarPorId(any())).thenReturn(cliente);

        Cliente clienteTeste = clienteService.consultaClientePorId(id);

        Assertions.assertEquals(id, clienteTeste.getId());
        Assertions.assertEquals(nome, clienteTeste.getNome());
        Assertions.assertEquals(inativo, clienteTeste.isInativo());
        Assertions.assertEquals(cpf, clienteTeste.getCpf());
        Assertions.assertEquals(email, clienteTeste.getEmail());
        Assertions.assertEquals(numeroTelefone, clienteTeste.getNumeroTelefone());
    }

    @Test
    void testeMetodoDeletarCliente(){
        Long id = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "12345678-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)99874-8356";

        Optional<Cliente> clienteOptional = Optional.of(new Cliente(id, nome, inativo, cpf, email, numeroTelefone));

        Mockito.when(clienteDataProvider.consultarPorId(any())).thenReturn(clienteOptional);
        Cliente clienteTeste = (Cliente) Mockito.when(clienteDataProvider.salvar(any())).thenReturn(clienteService.deletarCliente(id));

        Assertions.assertTrue(clienteTeste.isInativo());
    }

    @Test
    void alterarCliente(){
    }
}