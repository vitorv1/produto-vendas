package com.example.produtovendas.controller;


import com.example.produtovendas.domain.Venda;
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
    public ResponseEntity<Venda> cadastroVenda(@RequestBody Venda venda, UriComponentsBuilder uriBuilder){
        Venda vendaBody = vendaService.cadastroVenda(venda);
        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(vendaBody.getId()).toUri();
        return ResponseEntity.created(uri).body(vendaBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable("id") Long id){
        Optional<Venda> venda = vendaService.buscarPorId(id);
        return venda.map(vendaMap -> ResponseEntity.ok(vendaMap))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<List<Venda>> buscarTodos(){
        return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable("id") Long id){
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Venda> alterarVenda(@PathVariable("id") Long id, @RequestBody Venda venda){
        return ResponseEntity.ok(vendaService.alterarVenda(id, venda));
    }
}
