package com.example.produtovendas.infra.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Produto")
@Table(name = "produtos")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean inativo;
    private String marca;
    private double valor;

    public ProdutoEntity(Long id, String nome, String marca, double valor) {
        this.inativo = false;
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }

    public boolean getInativo(){
        return this.inativo;
    }
}
