package com.example.produtovendas.dto;

import lombok.*;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Cliente {

    private String nome;
    private String cpf;
    private String email;
    private String numero_telefone;
}
