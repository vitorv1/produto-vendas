package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    public static final String MENSAGEM_CLIENTE_NAO_EXISTENTE = "Cliente não existente";
    public static final String MENSAGEM_CLIENTE_EXISTENTE = "Cliente já existe no banco de dados";

    private final ClienteDataProvider clienteDataProvider;

    public Cliente cadastroCliente(Cliente cliente) {
        Optional<Cliente> clientePorCpf = clienteDataProvider.consultarPorCpf(cliente.getCpf());
        clientePorCpf.ifPresent(clienteExistente -> {
            throw new RuntimeException(MENSAGEM_CLIENTE_EXISTENTE);
        });
        return clienteDataProvider.salvar(cliente);
    }

    public List<Cliente> consultaTodosClientes() {
        return clienteDataProvider.consultarTodos();
    }

    public Optional<Cliente> consultaClientePorId(Long id) {
        return clienteDataProvider.consultarPorId(id);
    }

    public Cliente deletarCliente(Long id) {
        Cliente cliente = consultaClienteExistentePorId(id);
        cliente.inativar();
        return clienteDataProvider.salvar(cliente);
    }

    public Cliente alterarCliente(Long id, Cliente clienteAlterado) {
        Cliente cliente = consultaClienteExistentePorId(id);
        cliente.atualizarDados(clienteAlterado);
        return clienteDataProvider.salvar(cliente);
    }

    public Cliente consultaClienteExistentePorId(Long id) {
        return consultaClientePorId(id).orElseThrow(() -> new RuntimeException(MENSAGEM_CLIENTE_NAO_EXISTENTE));
    }
}
