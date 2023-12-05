package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.dtos.ClienteDto;
import com.example.produtovendas.infra.dataproviders.ClienteDataProvider;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import jakarta.persistence.EntityNotFoundException;
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

    public List<ClienteDto> consultaTodosClientes() {
        return ClienteMapper.paraDtosDeDomains(clienteDataProvider.consultarTodos());
    }

    public ClienteDto consultaClientePorId(Long id) {
        Optional<Cliente> cliente = clienteDataProvider.consultarPorId(id);
        if(cliente.isPresent()){
            return ClienteMapper.paraDtoDeDomain(cliente.get());
        }else {
            throw new EntityNotFoundException(MENSAGEM_CLIENTE_NAO_EXISTENTE);
        }
    }

    public void deletarCliente(Long id) {
        Cliente cliente = consultaClienteExistentePorId(id);
        cliente.inativar();
        clienteDataProvider.salvar(cliente);
    }

    public ClienteDto alterarCliente(Long id, ClienteDto clienteAlterado) {
        Cliente cliente = consultaClienteExistentePorId(id);
        cliente.atualizarDados(ClienteMapper.paraDomainDeDto(clienteAlterado));
        return ClienteMapper.paraDtoDeDomain(clienteDataProvider.salvar(cliente));
    }

    public Cliente consultaClienteExistentePorId(Long id) {
        return ClienteMapper.paraDomainDeDto(consultaClientePorId(id));
    }
}
