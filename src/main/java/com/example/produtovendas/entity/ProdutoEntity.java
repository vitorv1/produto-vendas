package com.example.produtovendas.entity;

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
    private String marca;
    private double valor;

    public void atualizaDados(String nome, String marca, double valor) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
}
