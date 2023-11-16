package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.ClienteDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private String cpf;
    @NotBlank
    @Email
    private String email;
    private String numeroTelefone;

    public void inativar() {
        this.inativo = true;
    }

    public void atualizarDados(ClienteDto clienteAlterado) {
        this.inativo = false;
        this.nome = clienteAlterado.nome();
        this.cpf = clienteAlterado.cpf();
        this.email = clienteAlterado.email();
        this.numeroTelefone = clienteAlterado.numeroTelefone();
    }
}
