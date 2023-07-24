package com.example.produtovendas.controller;
import com.example.produtovendas.dto.Cliente;
import com.example.produtovendas.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @PostMapping
    public void cadastro(@RequestBody Cliente cliente){
        clienteService.cadastrar(cliente);
    }





}
