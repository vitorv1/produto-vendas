package com.example.produtovendas.controller;


import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> cadastroVenda(@RequestBody Venda venda) {
        return new ResponseEntity<>(vendaService.cadastroVenda(venda), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vendaService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> buscarTodos() {
        return new ResponseEntity<>(vendaService.buscarTodos(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable("id") Long id) {
        vendaService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Venda> alterarVenda(@PathVariable("id") Long id, @RequestBody Venda venda) {
        return new ResponseEntity<>(vendaService.alterarVenda(id, venda), HttpStatus.OK);
    }

}
