package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Cliente;
public class ClienteMapper {



    public static Cliente paraCliente(ClienteEntity clienteEntity){
        return new Cliente(clienteEntity.getNome(), clienteEntity.getCpf(), clienteEntity.getEmail(), clienteEntity.getNumero_telefone());
    }

    public static ClienteEntity paraEntity(Cliente clientes){
        return new ClienteEntity(clientes);
    }
}
