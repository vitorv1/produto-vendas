package com.example.produtovendas.domain;


import com.example.produtovendas.controller.ClienteController;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private Integer desconto;
    private List<Produto> listaProdutos;
    private LocalDate dataVenda;
}
