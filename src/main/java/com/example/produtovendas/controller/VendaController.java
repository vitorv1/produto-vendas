package com.example.produtovendas.controller;


import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.entity.VendaEntity;
import com.example.produtovendas.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private VendaService vendaService;


    @PostMapping
    public ResponseEntity<Venda> cadastroVenda(@RequestBody Venda venda){
        return new ResponseEntity<>(vendaService.cadastroVenda(venda), HttpStatus.CREATED);
    }

}
