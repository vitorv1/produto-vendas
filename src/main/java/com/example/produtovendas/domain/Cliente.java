package com.example.produtovendas.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
public class Cliente {

    private Long id;
    @NotBlank
    private String nome;
    private boolean inativo;
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    @NotBlank
    @Email
    private String email;
    private String numeroTelefone;
}
