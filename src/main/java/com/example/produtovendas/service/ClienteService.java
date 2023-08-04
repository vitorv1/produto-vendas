package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.ClienteMapper;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente cadastroCliente(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);
        repository.save(clienteEntity);
        return ClienteMapper.paraCliente(repository.findById(clienteEntity.getId()).get());
    }

    public List<Cliente> consultaTodosClientes() {
        List<ClienteEntity> clienteEntities= repository.findAll().stream().filter(ClienteEntity -> !ClienteEntity.getInativo()).toList();
        return ClienteMapper.paraClientes(repository.findAll());
    }

    public Cliente consultaClientePorId(Long id) {
        ClienteEntity clienteEntity = repository.findById(id).get();
        if(clienteEntity.getInativo()){
            throw new RuntimeException("Cliente inativo");
        }else {
            return ClienteMapper.paraCliente(clienteEntity);
        }
    }

    public void deletarCliente(Long id) {
        ClienteEntity clienteEntity = repository.findById(id).get();
        clienteEntity.setInativo(false);
        repository.save(clienteEntity);
    }

    public Cliente alterarCliente(Long id, Cliente cliente) {
        ClienteEntity clienteEntity = repository.findById(id).get();
        clienteEntity.atualizaDados(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getNumeroTelefone());
        repository.save(clienteEntity);
        return ClienteMapper.paraCliente(clienteEntity);
    }
}
