package com.example.produtovendas.domain;


import com.example.produtovendas.entity.ProdutoEntity;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Produtos {

    private String nome;
    private String marca;
    private double valor;

    public Produtos(ProdutoEntity produtoEntity){
        this.nome = produtoEntity.getNome();
        this.marca = produtoEntity.getMarca();
        this.valor = produtoEntity.getValor();
    }
}
