package com.example.produtovendas.controller;


import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaDto> cadastroVenda(@RequestBody VendaDto vendaDto, UriComponentsBuilder uriBuilder){
        VendaDto vendaBody = vendaService.cadastroVenda(vendaDto);
        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(vendaBody.id()).toUri();
        return ResponseEntity.created(uri).body(vendaBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDto> buscarPorId(@PathVariable("id") Long id){
        VendaDto vendaDto = vendaService.buscarPorId(id);
        return ResponseEntity.ok().body(vendaDto);
    }

    @GetMapping
    public ResponseEntity<List<VendaDto>> buscarTodos(){
        return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable("id") Long id){
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendaDto> alterarVenda(@PathVariable("id") Long id, @RequestBody VendaDto vendaDto){
        return ResponseEntity.ok(vendaService.alterarVenda(id, vendaDto));
    }
}
