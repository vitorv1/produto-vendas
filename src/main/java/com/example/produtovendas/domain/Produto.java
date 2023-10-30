package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.ProdutoDto;
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
        this.nome = produtoDto.nome();
        this.marca = produtoDto.marca();
        this.valor = produtoDto.valor();
        this.quantidade = produtoDto.quantidade();
    }
    public void inativar() {
        this.inativo = true;
    }
}
