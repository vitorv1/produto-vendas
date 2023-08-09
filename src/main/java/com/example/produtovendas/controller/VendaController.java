package com.example.produtovendas.controller;


import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> cadastroVenda(@RequestBody Venda venda, UriComponentsBuilder uriBuilder) throws Exception {
        Venda vendaBody = vendaService.cadastroVenda(venda);
        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(vendaBody.getId()).toUri();
        return ResponseEntity.created(uri).body(vendaBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable("id") Long id)throws Exception {
        Venda venda = vendaService.buscarPorId(id);
        if(venda == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(venda);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> buscarTodos() throws Exception {
        return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable("id") Long id) throws Exception{
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Venda> alterarVenda(@PathVariable("id") Long id, @RequestBody Venda venda) throws Exception{
        return ResponseEntity.ok(vendaService.alterarVenda(id, venda));
    }

}
