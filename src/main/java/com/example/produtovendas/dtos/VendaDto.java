package com.example.produtovendas.dtos;

import com.example.produtovendas.domain.Produto;
import lombok.Builder;

import java.util.List;

@Builder
public record VendaDto(Long id, Long idCliente, Integer desconto, List<Produto> produtoList) {
}
