package com.example.produtovendas.dto;


import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Produto {

    private String nome;
    private String marca;
    private double valor;
}
