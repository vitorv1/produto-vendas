package com.example.produtovendas.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.ClienteMapper;
import com.example.produtovendas.infra.BancoDeDadosException;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente cadastroCliente(Cliente cliente) throws SQLException{
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);
        try {
            repository.save(clienteEntity);
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        try {
            return ClienteMapper.paraCliente(repository.findById(clienteEntity.getId()).get());
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
    }

    public List<Cliente> consultaTodosClientes() throws SQLException{
        List<ClienteEntity> clienteEntities;
        try {
             clienteEntities = repository.findAll().stream().filter(ClienteEntity -> !ClienteEntity.getInativo()).toList();
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        return ClienteMapper.paraClientes(clienteEntities);
    }

    public Cliente consultaClientePorId(Long id) throws Exception{
        ClienteEntity clienteEntity;
        try{
             clienteEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        if(clienteEntity.getInativo()){
            throw new RuntimeException("Cliente inativo");
        }else {
            return ClienteMapper.paraCliente(clienteEntity);
        }
    }

    public void deletarCliente(Long id) throws SQLException {
        ClienteEntity clienteEntity;
        try{
            clienteEntity = repository.findById(id).get();
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        clienteEntity.setInativo(false);
        try{
            repository.save(clienteEntity);
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
    }

    public Cliente alterarCliente(Long id, Cliente cliente) throws SQLException{
        ClienteEntity clienteEntity;
        try{
            clienteEntity = repository.findById(id).get();
        }catch (Exception ex) {
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        clienteEntity.atualizaDados(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getNumeroTelefone());
        try{
            repository.save(clienteEntity);
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        return ClienteMapper.paraCliente(clienteEntity);
    }
}
