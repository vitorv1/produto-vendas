package com.example.produtovendas.domain;

import com.example.produtovendas.entity.ClienteEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Getter
@NoArgsConstructor
@ToString
@Setter
public class Cliente {

    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    @NotBlank
    @Email
    private String email;
    private String numero_telefone;

    public Cliente(String nome, String cpf, String email, String numero_telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.numero_telefone = numero_telefone;
    }
}
