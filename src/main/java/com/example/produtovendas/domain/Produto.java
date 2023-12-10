package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.infra.validacoes.AtributeValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Builder
public class Produto {

    private Long id;
    @NotBlank
    private String nome;
    private boolean inativo;
    @NotBlank
    private String marca;
    private BigDecimal valor;
    private Integer quantidade;

    public void atualizaDados(Produto produtoDto) {
        if(AtributeValidation.stringValidation(produtoDto.getNome()))
            this.nome = produtoDto.getNome();
        if(AtributeValidation.stringValidation(produtoDto.getMarca()))
            this.marca = produtoDto.getMarca();
        if(AtributeValidation.bigDecimalValidation(produtoDto.getValor()))
            this.valor = produtoDto.getValor();
        if(AtributeValidation.integerValidation(produtoDto.getQuantidade()))
            this.quantidade = produtoDto.getQuantidade();
    }

    public void inativar() {
        this.inativo = true;
    }

    public void ativar() {
        this.inativo = false;
    }
}
