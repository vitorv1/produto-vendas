package com.example.produtovendas.service;

import com.example.produtovendas.domain.Clientes;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.ClienteMapper;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository ;

    public void cadastrar(Clientes cliente){
        repository.save(ClienteMapper.paraEntity(cliente));
    }
}
