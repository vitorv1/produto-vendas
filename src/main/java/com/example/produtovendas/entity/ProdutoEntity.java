package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Produto;
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
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private double valor;

    public ProdutoEntity(String nome, String marca, double valor) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }

    public void atualizaDados(String nome, String marca, double valor){
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
}
