package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.validacoes.ClienteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteDataProvider clienteDataProvider;

    public Cliente cadastroCliente(Cliente cliente) {
        List<Cliente> clientes ;
        clientes = clienteDataProvider.consultarTodos();
        ClienteValidation.validaCliente(clientes, cliente);
        return clienteDataProvider.salvar(cliente);
    }

    public List<Cliente> consultaTodosClientes() {
        return clienteDataProvider.consultarTodos();
    }

    public Cliente consultaClientePorId(Long id) {
        return clienteDataProvider.consultarPorId(id).orElseThrow(() -> new RuntimeException("Cliente não existente"));
    }

    public void deletarCliente(Long id) {
        Cliente cliente = consultaClientePorId(id);
        cliente.inativar();
        clienteDataProvider.salvar(cliente);
    }

    public Cliente alterarCliente(Long id, Cliente clienteAlterado) {
        Cliente cliente = consultaClientePorId(id);
        cliente.atualizarDados(clienteAlterado);
        return clienteDataProvider.salvar(cliente);
    }
}
