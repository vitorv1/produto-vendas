package com.example.produtovendas.entity;


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

    public VendaEntity (Long id, ClienteEntity clienteEntity, double valor, Integer desconto, List<ProdutoEntity> listaProdutos, LocalDate dataVenda){
        this.inativo = false;
        this.id = id;
        this.clienteEntity  = clienteEntity;
        this.valor = valor;
        this.desconto = desconto;
        this.listaProdutos = listaProdutos;
        this.dataVenda = dataVenda;
    }

    public boolean getInativo(){
        return this.inativo;
    }
}



