package com.example.produtovendas.infra.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Produto")
@Table(name = "produtos")
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
