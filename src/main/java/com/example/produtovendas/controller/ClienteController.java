package com.example.produtovendas.controller;

import  com.example.produtovendas.dtos.ClienteDto;
import com.example.produtovendas.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> cadastroCliente(@RequestBody @Valid ClienteDto dto, UriComponentsBuilder uriBuilder) {
        ClienteDto clienteBody = clienteService.cadastroCliente(dto);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteBody.id()).toUri();
        return ResponseEntity.created(uri).body(clienteBody);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> consultarTodosClientes() {
        return ResponseEntity.ok(clienteService.consultaTodosClientes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> consultaClientePorId(@PathVariable("id") Long id) {
        ClienteDto clienteDto = clienteService.consultaClientePorId(id);
        return ResponseEntity.ok().body(clienteDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> alterarCliente(@PathVariable("id") Long id, @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.alterarCliente(id, clienteDto));
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarCliente(@PathVariable("id") Long id){
        clienteService.ativarCliente(id);
        return ResponseEntity.ok().build();
    }
}
