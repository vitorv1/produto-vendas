package com.example.produtovendas.controller;
import com.example.produtovendas.domain.Clientes;
import com.example.produtovendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public void cadastro(@RequestBody Clientes cliente){
        clienteService.cadastrar(cliente);
    }





}
