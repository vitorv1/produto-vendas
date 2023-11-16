package com.example.produtovendas.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.example.produtovendas.infra.validacoes.ObjectValidation.validaObjeto;

@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
public class Venda {

    private Long id;
    private Long idCliente;
    private Cliente cliente;
    private BigDecimal valor;
    private boolean inativo;
    private Integer desconto;
    private List<Produto> listaProdutos;
    private LocalDate dataVenda;

    public void inativar() {
        this.inativo = true;
    }

    public void atualizaDados(Venda vendaDto) {
        /*this.inativo = false;
        if(validaObjeto(vendaDto.getCliente()))
            this.cliente = vendaDto.getCliente();
        if(vendaDto.getIdCliente() != null)
            this.idCliente = vendaDto.getIdCliente();
        this.valor = vendaDto.getValor();
        if(vendaDto.getDesconto() != null)
            this.desconto = vendaDto.getDesconto();
        if(vendaDto.getListaProdutos() != null)
            this.listaProdutos = vendaDto.getListaProdutos();

        validacao(validaObjeto(vendaDto), vendaDto);
        this.inativo = false;
        this.valor = vendaDto.getValor();*/
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

    private void validacao (boolean validacao, Venda vendaDto){
        if(validacao){
            this.cliente = vendaDto.getCliente();
            this.idCliente = vendaDto.getIdCliente();
            this.desconto = vendaDto.getDesconto();
            this.listaProdutos = vendaDto.getListaProdutos();
        }
    }

    private void validacao2(boolean validacaoVar, Object domain, Object dto){
        if(validacaoVar){

        }
    }
}
