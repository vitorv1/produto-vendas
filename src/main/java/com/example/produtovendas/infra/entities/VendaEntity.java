package com.example.produtovendas.infra.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "venda")
@Table(name = "vendas")
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity clienteEntity;
    private boolean inativo;
    private double valor;
    private Integer desconto;
    @ManyToMany
    private List<ProdutoEntity> listaProdutos;
    private LocalDate dataVenda;
}



