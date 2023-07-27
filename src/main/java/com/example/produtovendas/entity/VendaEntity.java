package com.example.produtovendas.entity;


import com.example.produtovendas.domain.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "venda")
@Table(name = "vendas")
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@Setter
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Cliente cliente;
    private double valor;
    private Integer desconto;
    @OneToMany
    private List<ProdutoEntity> listaProdutos;
    private LocalDate dataVenda;


    public VendaEntity (Cliente cliente, double valor, Integer desconto, List<ProdutoEntity> listaProdutos, LocalDate dataVenda){
        this.cliente = cliente;
        this.valor = valor;
        this.desconto = desconto;
        this.listaProdutos = listaProdutos;
        this.dataVenda = dataVenda;
    }

}
