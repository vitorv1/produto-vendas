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

    public void atualizaDados(ProdutoDto produtoDto) {
        this.inativo = false;
        if(AtributeValidation.stringValidation(produtoDto.nome()))
            this.nome = produtoDto.nome();
        if(AtributeValidation.stringValidation(produtoDto.marca()))
            this.marca = produtoDto.marca();
        if(AtributeValidation.bigDecimalValidation(produtoDto.valor()))
            this.valor = produtoDto.valor();
        if(AtributeValidation.integerValidation(produtoDto.quantidade()))
            this.quantidade = produtoDto.quantidade();
    }

    public void inativar() {
        this.inativo = true;
    }
}
