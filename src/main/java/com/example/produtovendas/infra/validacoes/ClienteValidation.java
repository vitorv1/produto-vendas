package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteValidation {

    public static void validaCliente(List<Cliente> clienteList, Cliente cliente){
        System.out.println(clienteList);
        System.out.println(cliente);
        Optional<Cliente> clienteOptional = clienteList.stream().filter(Cliente -> Cliente.getCpf().equalsIgnoreCase(cliente.getCpf())).findFirst();
        clienteOptional.ifPresent(Cliente -> {throw new RuntimeException("Cliente já existe no banco de dados");});
    }
}
