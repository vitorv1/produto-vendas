package com.example.produtovendas.domain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@NoArgsConstructor
@ToString
@Setter
public class Cliente {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    @NotBlank
    @Email
    private String email;
    private String numeroTelefone;

    public Cliente(Long id, String nome, String cpf, String email, String numeroTelefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
    }
}
