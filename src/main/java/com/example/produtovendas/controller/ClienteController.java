package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastroCliente(@RequestBody @Valid Cliente cliente) {
        return new ResponseEntity<>(clienteService.cadastroCliente(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> concultarTodosClientes() {
        return new ResponseEntity<>(clienteService.consultaTodosClientes(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> concultaClientePorId(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.consultaClientePorId(id);
                if(cliente == null){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarCliente(@PathVariable("id") Long id){
        clienteService.deletarCliente(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> altararCliente(@PathVariable("id")Long id, @RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.alterarCliente(id, cliente), HttpStatus.OK);
    }
}
