package com.example.produtovendas.infra.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Estoque")
@Table(name = "estoques")
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    @OneToOne
    private ProdutoEntity produtoEntity;
}
