package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.infra.validacoes.ClienteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    public static final String MENSAGEM_CLIENTE_EXISTENTE = "Cliente não existente";
    private final ClienteDataProvider clienteDataProvider;

    @Autowired
    public ClienteService (ClienteDataProvider clienteDataProvider){
        this.clienteDataProvider = clienteDataProvider;
    }

    public Cliente cadastroCliente(Cliente cliente) {
        List<Cliente> clientes ;
        clientes = clienteDataProvider.consultarTodos();
        ClienteValidation.validaCliente(clientes, cliente);
        System.out.println("Service: " + cliente);
        return clienteDataProvider.salvar(cliente);
    }

    public List<Cliente> consultaTodosClientes() {
        return clienteDataProvider.consultarTodos();
    }

    public Cliente consultaClientePorId(Long id) {
        return clienteDataProvider.consultarPorId(id).orElseThrow(() -> new RuntimeException(MENSAGEM_CLIENTE_EXISTENTE));
    }

    public Cliente deletarCliente(Long id) {
        Cliente cliente = consultaClientePorId(id);
        cliente.inativar();
        return clienteDataProvider.salvar(cliente);
    }

    public Cliente alterarCliente(Long id, Cliente clienteAlterado) {
        Cliente cliente = consultaClientePorId(id);
        cliente.atualizarDados(clienteAlterado);
        return clienteDataProvider.salvar(cliente);
    }
}
