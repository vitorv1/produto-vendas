package com.example.produtovendas.dtos;

import com.example.produtovendas.domain.Produto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record VendaDto(Long id, Long idCliente, ClienteDto cliente, BigDecimal valor, Integer desconto, List<ProdutoDto> listaProdutos, String dataVenda) {
}
