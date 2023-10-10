package com.example.produtovendas.domain;

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
        this.inativo = false;
        this.nome = produtoDto.getNome();
        this.marca = produtoDto.getMarca();
        this.valor = produtoDto.getValor();
        this.quantidade = produtoDto.getQuantidade();
    }
    public void inativar() {
        this.inativo = true;
    }
}
