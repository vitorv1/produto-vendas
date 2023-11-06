package com.example.produtovendas.dtos;

import com.example.produtovendas.domain.Produto;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record VendaDto(Long id, Long idCliente, BigDecimal valor, boolean inativo , Integer desconto, List<Produto> listaProdutos, LocalDate dataVenda) {
}
