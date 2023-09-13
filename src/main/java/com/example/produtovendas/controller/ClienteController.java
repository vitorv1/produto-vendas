package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastroCliente(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
        System.out.println("controller " + cliente);
        Cliente clienteBody = clienteService.cadastroCliente(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteBody.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteBody);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarTodosClientes(){
        return ResponseEntity.ok(clienteService.consultaTodosClientes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> consultaClientePorId(@PathVariable("id") Long id){
        Cliente cliente = clienteService.consultaClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> altararCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.alterarCliente(id, cliente));
    }
}
