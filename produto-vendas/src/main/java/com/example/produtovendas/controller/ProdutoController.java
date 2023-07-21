package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(repository.save(produto), HttpStatus.CREATED);
    }
}
