package com.example.produtovendas.dtos;

import com.example.produtovendas.domain.Cliente;
import lombok.Builder;

@Builder
public record ClienteDto(Long id, String nome, String cpf, String email, String numeroTelefone){
}
