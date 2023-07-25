package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public void cadastrar(@RequestBody Produto produto){
        produtoService.cadastrar(produto);
    }
}
