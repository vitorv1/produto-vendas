package com.example.produtovendas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Setter
public class Produto {

    private Long id;
    private String nome;
    private String marca;
    private double valor;

    public Produto(Long id, String nome, String marca, double valor) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
}
