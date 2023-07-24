package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente){
        return new ResponseEntity<>(repository.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
        Cliente cliente1 = repository.getReferenceById(cliente.getId());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<Cliente> deletarCliente(@RequestBody Cliente cliente){
        return new ResponseEntity<>(repository.delete(cliente), HttpStatus.ACCEPTED);
    }
}
