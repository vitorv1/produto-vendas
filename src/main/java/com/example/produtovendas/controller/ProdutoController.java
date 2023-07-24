package com.example.produtovendas.controller;

import com.example.produtovendas.dto.Produto;
import com.example.produtovendas.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    @PostMapping
    public void cadastrar(@RequestBody Produto produto){
        produtoService.cadastrar(produto);
    }
}
