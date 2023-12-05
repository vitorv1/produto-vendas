package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.validacoes.AtributeValidation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
public class Venda  {

    private Long id;
    private Long idCliente;
    private Cliente cliente;
    private BigDecimal valor;
    private Integer desconto;
    private List<Produto> listaProdutos;
    private LocalDate dataVenda;

    public void calcularValorVenda() {
        double resultado;
        double valorSomaProdutos = 0;
        for (Produto produto : this.listaProdutos) {
            if(AtributeValidation.bigDecimalValidation(produto.getValor()))
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

    public void atualizaDados(Venda vendaDto) {
        if(AtributeValidation.objectValidation(vendaDto.getCliente()))
            this.cliente = vendaDto.getCliente();
        if(AtributeValidation.longValidation(vendaDto.getIdCliente()))
            this.idCliente = vendaDto.getIdCliente();
        if(AtributeValidation.integerValidation(vendaDto.getDesconto()))
            this.desconto = vendaDto.getDesconto();
       if(AtributeValidation.listValidation(vendaDto.getListaProdutos()))
            this.listaProdutos = vendaDto.getListaProdutos();
        if(AtributeValidation.bigDecimalValidation(vendaDto.getValor()))
            this.valor = vendaDto.getValor();
    }

}
