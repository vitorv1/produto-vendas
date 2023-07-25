package com.example.produtovendas.controller;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.service.ClienteService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public void cadastro(@RequestBody @Valid  Cliente cliente){
        clienteService.cadastrar(cliente);
    }

    /*@GetMapping
    public ResponseEntity<Cliente>getCliente(){

    }*/





}
