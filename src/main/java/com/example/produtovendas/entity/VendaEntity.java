package com.example.produtovendas.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "venda")
@Table(name = "vendas")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
