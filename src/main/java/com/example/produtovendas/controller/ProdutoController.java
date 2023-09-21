package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastroProduto(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder){
        Produto produtoBody = produtoService.cadastroProduto(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoBody.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> consultarProdutoPorId(@PathVariable("id") Long id){
        Optional<Produto> produto = produtoService.consultarProdutoPorId(id);
        return produto.map(produtoMap -> ResponseEntity.ok(produtoMap))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> consultarTodosProdutos(){
        return ResponseEntity.ok(produtoService.consultaTodosProdutos());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable("id") Long id, @RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.alterarProduto(id, produto));
    }
}
