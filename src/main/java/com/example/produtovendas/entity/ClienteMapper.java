package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Clientes;
public class ClienteMapper {



    public static Clientes paraCliente(ClienteEntity clienteEntity){
        return new Clientes(clienteEntity);
    }

    public static ClienteEntity paraEntity(Clientes clientes){
        return new ClienteEntity(clientes);
    }
}
