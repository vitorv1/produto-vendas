package com.example.produtovendas.controller;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.dtos.ProdutoDto;
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
    public ResponseEntity<ProdutoDto> cadastroProduto(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder){
        ProdutoDto produtoBody = produtoService.cadastroProduto(produtoDto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoBody.id()).toUri();
        return ResponseEntity.created(uri).body(produtoBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> consultarProdutoPorId(@PathVariable("id") Long id){
        ProdutoDto produtoDto = produtoService.consultarProdutoPorId(id);
        return ResponseEntity.ok().body(produtoDto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> consultarTodosProdutos(){
        return ResponseEntity.ok(produtoService.consultaTodosProdutos());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable("id") Long id, @RequestBody ProdutoDto produtoDto){
        return ResponseEntity.ok(produtoService.alterarProduto(id, produtoDto));
    }
}
