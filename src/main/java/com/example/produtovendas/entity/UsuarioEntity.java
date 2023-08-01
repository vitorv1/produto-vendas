package com.example.produtovendas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="Usuario")
@Table(name="usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@ToString
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
}
