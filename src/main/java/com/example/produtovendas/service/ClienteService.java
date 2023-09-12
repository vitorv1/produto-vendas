package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteDataProvider clienteDataProvider;

    public Cliente cadastroCliente(Cliente cliente) {
        return clienteDataProvider.cadastrarCliente(cliente);
    }

    public List<Cliente> consultaTodosClientes() {
        return clienteDataProvider.consultarTodos();
    }

    public Cliente consultaClientePorId(Long id) {
        return buscarCliente(id);
    }

    public Cliente deletarCliente(Long id) {
        return clienteDataProvider.deletarCliente(id);
    }

    public Cliente alterarCliente(Long id, Cliente clienteAlterado) {
         return clienteDataProvider.aterarCliente(id, clienteAlterado);
    }

    private Cliente buscarCliente(Long id) {
        return clienteDataProvider.consultarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não existente"));
    }
}
