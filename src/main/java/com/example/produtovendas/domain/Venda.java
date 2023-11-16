package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.ProdutoDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
public class Venda {

    private Long id;
    private Cliente cliente;
    private Long idCliente;
    private BigDecimal valor;
    private boolean inativo;
    private Integer desconto;
    private List<Produto> listaProdutos;
    private LocalDate dataVenda;

    public void inativar() {
        this.inativo = true;
    }

    public void atualizaDados(Venda vendaDto) {
        this.inativo = false;
        if()
        this.cliente = vendaDto.getCliente();
        if(vendaDto.getIdCliente() != null){
            this.idCliente = vendaDto.getIdCliente();
        }
        this.valor = vendaDto.getValor();
        if(vendaDto.getDesconto() != null){
            this.desconto = vendaDto.getDesconto();
        }
        if(vendaDto.getListaProdutos() != null){
            for (Produto produto: vendaDto.getListaProdutos()) {
                if(produto != null){
                    this.listaProdutos = vendaDto.getListaProdutos();
                }
            }
        }
    }

    public void calcularValorVenda() {
        double resultado;
        double valorSomaProdutos = 0;
        for (Produto produto : this.listaProdutos) {
            valorSomaProdutos += produto.getValor().doubleValue();
        }

        if (this.desconto > 0) {
            double valorDesconto = (valorSomaProdutos * this.desconto) / 100;
            resultado = valorSomaProdutos - valorDesconto;
        } else {
            resultado = valorSomaProdutos;
        }

        this.valor = BigDecimal.valueOf(resultado);
    }
}
