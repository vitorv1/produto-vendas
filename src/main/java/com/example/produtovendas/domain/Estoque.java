package com.example.produtovendas.domain;

import com.example.produtovendas.infra.entities.ProdutoEntity;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Builder
public class Estoque {

    private Long id;
    private Integer quantidade;
    private Produto produto;
}
