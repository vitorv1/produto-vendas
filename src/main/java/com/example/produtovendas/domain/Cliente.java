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
    //@Pattern(regexp = "\\d{12}")
    private String cpf;
    @NotBlank
    @Email
    private String email;
    private String numeroTelefone;

    public void inativar() {
        this.inativo = true;
    }

    public void atualizarDados(Cliente clienteAlterado) {
        this.inativo = false;
        this.nome = clienteAlterado.getNome();
        this.cpf = clienteAlterado.getCpf();
        this.email = clienteAlterado.getEmail();
        this.numeroTelefone = clienteAlterado.getNumeroTelefone();
    }
}
