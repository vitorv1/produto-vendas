package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public void cadastrar(@RequestBody Produto produto){
        produtoService.cadastrar(produto);
    }

    @GetMapping
    public List<Produto> getProduto(){
        return produtoService.getProduto();
    }


}
