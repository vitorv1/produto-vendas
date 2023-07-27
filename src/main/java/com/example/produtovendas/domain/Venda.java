package com.example.produtovendas.domain;


import com.example.produtovendas.controller.ClienteController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@Setter
public class Venda {

    private Long id;
    private Cliente cliente;
    private Long idCliente;
    private double valor;
    private Integer desconto;
    private List<Produto> listaProdutos = new ArrayList<>();
    private LocalDate dataVenda;

}
