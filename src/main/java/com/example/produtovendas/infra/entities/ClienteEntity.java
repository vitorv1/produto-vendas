package com.example.produtovendas.infra.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Cliente")
@Table(name = "clientes")
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean inativo;
    private String cpf;
    private String email;
    private String numeroTelefone;

}
