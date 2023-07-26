package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastroProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.cadastroProduto(produto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> consultarProdutoPorId(@PathVariable("id") Long id){
        Produto produto = produtoService.consultarProdutoPorId(id);
        if(produto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> consultarTodosProdutos(){
        return new ResponseEntity<>(produtoService.consultaTodosProdutos(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarProduto(@PathVariable("id") Long id){
        produtoService.deletarProduto(id);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable("id") Long id, @RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.alterarProduto(id, produto), HttpStatus.OK);
    }


}
