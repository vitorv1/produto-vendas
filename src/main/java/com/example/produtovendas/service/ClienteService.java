package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.ClienteMapper;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente cadastroCliente(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);
        repository.save(clienteEntity);
        return ClienteMapper.paraCliente(repository.getReferenceById(clienteEntity.getId()));
    }

    public List<Cliente> consultaTodosClientes() {
        return ClienteMapper.paraClientes(repository.findAll());
    }

    public Cliente consultaClientePorId(Long id) {
        return ClienteMapper.paraCliente(repository.getReferenceById(id));
    }

    public void deletarCliente(Long id){
        repository.deleteById(id);
    }

    public Cliente alterarCliente(Long id, Cliente cliente){
        ClienteEntity clienteEntity = repository.getReferenceById(id);
        clienteEntity.atualizaDados(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getNumeroTelefone());
        repository.save(clienteEntity);
        return ClienteMapper.paraCliente(clienteEntity);
    }
}
