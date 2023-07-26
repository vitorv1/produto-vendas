package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {



    public static Cliente paraCliente(ClienteEntity clienteEntity){
        return new Cliente(clienteEntity.getNome(), clienteEntity.getCpf(), clienteEntity.getEmail(), clienteEntity.getNumero_telefone());
    }

    public static ClienteEntity paraEntity(Cliente cliente){
        return new ClienteEntity(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getNumero_telefone());
    }
    public static List<Cliente> paraClientes(List<ClienteEntity> clienteEntities){
        List<Cliente>  clienteList = new ArrayList<>();
        for(ClienteEntity clienteEntity : clienteEntities){
            clienteList.add(ClienteMapper.paraCliente(clienteEntity));
        }
        return clienteList;
    }
}
