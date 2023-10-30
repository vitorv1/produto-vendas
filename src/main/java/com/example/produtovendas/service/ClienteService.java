package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.dtos.ClienteDto;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.infra.mappers.ClienteMapper;
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

    public ClienteDto cadastroCliente(ClienteDto clienteDto) {
        Cliente cliente = ClienteMapper.paraDomainDeDto(clienteDto);
        Optional<Cliente> clientePorCpf = clienteDataProvider.consultarPorCpf(cliente.getCpf());
        clientePorCpf.ifPresent(clienteExistente -> {
            throw new RuntimeException(MENSAGEM_CLIENTE_EXISTENTE);
        });
        return ClienteMapper.paraDtoDeDomain(clienteDataProvider.salvar(cliente));
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

    public ClienteDto alterarCliente(Long id, ClienteDto clienteAlterado) {
        Cliente cliente = consultaClienteExistentePorId(id);
        cliente.atualizarDados(clienteAlterado);
        return ClienteMapper.paraDtoDeDomain(clienteDataProvider.salvar(cliente));
    }

    public Cliente consultaClienteExistentePorId(Long id) {
        return consultaClientePorId(id).orElseThrow(() -> new RuntimeException(MENSAGEM_CLIENTE_NAO_EXISTENTE));
    }
}
