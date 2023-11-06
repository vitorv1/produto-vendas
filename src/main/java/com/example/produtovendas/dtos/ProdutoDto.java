package com.example.produtovendas.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoDto(Long id, String nome,boolean inativo ,String marca, BigDecimal valor, Integer quantidade){}
