package com.example.produtovendas.infra.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

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
