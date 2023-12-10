package com.example.produtovendas.dtos;

import lombok.Builder;

@Builder
public record ClienteDto(Long id, String nome,boolean inativo ,String cpf, String email, String numeroTelefone){
}
