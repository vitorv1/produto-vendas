package com.example.produtovendas.service;

import com.example.produtovendas.dto.Cliente;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public void cadastrar(Cliente cliente){
        repository.save(ClienteEntity.atualizaDados(cliente));
    }
}
