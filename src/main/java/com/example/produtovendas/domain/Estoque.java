package com.example.produtovendas.domain;

import lombok.*;

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
