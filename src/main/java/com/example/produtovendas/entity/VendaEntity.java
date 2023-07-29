package com.example.produtovendas.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "venda")
@Table(name = "vendas")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity clienteEntity;
    private double valor;
    private Integer desconto;
    @ManyToMany
    @JoinTable(name = "venda_produtos",
            joinColumns = {@JoinColumn(name = "id_venda")},
            inverseJoinColumns = {@JoinColumn(name = "id_produto")}
    )
    private List<ProdutoEntity> listaProdutos;
    private LocalDate dataVenda;
}
