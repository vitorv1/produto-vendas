package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static Cliente paraCliente(ClienteEntity clienteEntity) {
        return Cliente.builder()
                .id(clienteEntity.getId())
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .email(clienteEntity.getEmail())
                .numeroTelefone(clienteEntity.getNumeroTelefone())
                .build();
    }

    public static ClienteEntity paraEntity(Cliente cliente) {
        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .numeroTelefone(cliente.getNumeroTelefone())
                .build();
    }

    public static List<Cliente> paraClientes(List<ClienteEntity> clienteEntities) {
        return clienteEntities.stream().map(ClienteMapper::paraCliente).collect(Collectors.toList());
    }
}
