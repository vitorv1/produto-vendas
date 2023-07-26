package com.example.produtovendas.domain;


import com.example.produtovendas.entity.ProdutoEntity;
import lombok.*;


@Getter
@NoArgsConstructor
@ToString
@Setter
public class Produto {

    private String nome;
    private String marca;
    private double valor;

    public Produto(String nome, String marca, double valor){
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
}
