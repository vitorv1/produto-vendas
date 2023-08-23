package com.example.produtovendas.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
    private double valor;

    public void atualizaDados(Produto produtoDto) {
        this.inativo = false;
        this.nome = produtoDto.getNome();
        this.marca = produtoDto.getMarca();
        this.valor = produtoDto.getValor();
    }
    public void inativar() {
        this.inativo = true;
    }
}
