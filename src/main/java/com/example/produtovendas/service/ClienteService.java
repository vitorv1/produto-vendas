package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteDataProvider clienteDataProvider;

    public Cliente cadastroCliente(Cliente cliente) {
        return clienteDataProvider.salvar(cliente);
    }

    public List<Cliente> consultaTodosClientes() {
        return clienteDataProvider.consultarTodos();
    }

    public Cliente consultaClientePorId(Long id) {
        Cliente cliente = buscarCliente(id);

        if (cliente.isInativo()) {
            throw new RuntimeException("Cliente inativo");
        }

        return cliente;
    }

    public void deletarCliente(Long id) {
        Cliente cliente = buscarCliente(id);
        cliente.inativar();
        clienteDataProvider.salvar(cliente);
    }

    public Cliente alterarCliente(Long id, Cliente clienteAlterado) {
        Cliente cliente = buscarCliente(id);
        cliente.atualizarDados(clienteAlterado);
        return clienteDataProvider.salvar(cliente);
    }

    private Cliente buscarCliente(Long id) {
        return clienteDataProvider.consultarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não existente"));
    }
}
