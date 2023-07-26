package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.ClienteMapper;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository ;

    public void cadastrar(Cliente cliente){
        repository.save(ClienteMapper.paraEntity(cliente));
        System.out.println(repository);
    }


    public List<Cliente> getCliente(){
        return  ClienteMapper.paraClientes(repository.findAll());
    }

    public Cliente getClienteId(Long id) {
        return ClienteMapper.paraCliente(repository.getReferenceById(id));
    }
}
