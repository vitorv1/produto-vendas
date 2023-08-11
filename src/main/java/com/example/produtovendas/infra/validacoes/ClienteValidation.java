package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;

import java.util.List;
import java.util.Objects;

public class ClienteValidation {

    public static boolean validaCliente(List<Cliente> clienteList, Cliente cliente){
        boolean nome = false;
        boolean cpf = false;
        boolean email = false;
        boolean numeroTelefone = false;
        for(Cliente cliente1 : clienteList){
            if(Objects.equals(cliente1.getNome(), cliente.getNome())){
                nome = true;
            }
            if(Objects.equals(cliente1.getCpf(), cliente.getCpf())){
                cpf = true;
            }
            if(Objects.equals(cliente1.getEmail(), cliente.getEmail())){
                email = true;
            }
            if(Objects.equals(cliente1.getNumeroTelefone(), cliente.getNumeroTelefone())){
                numeroTelefone = true;
            }
        }
        if(nome && cpf && email && numeroTelefone){
            return true;
        }else {
            return false;
        }
    }
}
