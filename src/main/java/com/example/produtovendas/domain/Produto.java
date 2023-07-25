package com.example.produtovendas.domain;


import com.example.produtovendas.entity.ProdutoEntity;
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

    public Produto(ProdutoEntity produtoEntity){
        this.nome = produtoEntity.getNome();
        this.marca = produtoEntity.getMarca();
        this.valor = produtoEntity.getValor();
    }
}
