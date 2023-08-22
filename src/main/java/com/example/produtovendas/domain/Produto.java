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

    public void atualizaDados(String nome, String marca, double valor) {
        this.inativo = false;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
    public void inativar() {
        this.inativo = true;
    }
}
