package com.example.produtovendas.domain;

import com.example.produtovendas.dtos.ClienteDto;
import com.example.produtovendas.infra.validacoes.AtributeValidation;
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

    public void atualizarDados(Cliente clienteDto) {
        this.inativo = false;
        if(AtributeValidation.stringValidation(clienteDto.getNome()))
            this.nome = clienteDto.getNome();
        if(AtributeValidation.stringValidation(clienteDto.getCpf()))
            this.cpf = clienteDto.getCpf();
        if(AtributeValidation.stringValidation(clienteDto.getEmail()))
            this.email = clienteDto.getEmail();
        if(AtributeValidation.stringValidation(clienteDto.getNumeroTelefone()))
            this.numeroTelefone = clienteDto.getNumeroTelefone();
    }
}
