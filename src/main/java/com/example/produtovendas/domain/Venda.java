package com.example.produtovendas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class Venda {

    private Long id;
    private Cliente cliente;
    private Long idCliente;
    private double valor;
    private boolean inativo;
    private Integer desconto;
    private List<Produto> listaProdutos;
    private LocalDate dataVenda;

    public void inativar() {
        this.inativo = true;
    }

    public void atualizaDados(Long id, Cliente cliente, Long idCliente, double valor, boolean inativo, Integer desconto, List<Produto> listaProdutos, LocalDate dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.idCliente = idCliente;
        this.valor = valor;
        this.inativo = inativo;
        this.desconto = desconto;
        this.listaProdutos = listaProdutos;
        this.dataVenda = dataVenda;
    }
}
