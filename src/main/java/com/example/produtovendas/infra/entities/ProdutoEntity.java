package com.example.produtovendas.infra.entities;

import com.example.produtovendas.domain.Estoque;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal valor;
    private Integer quantidade;
}
