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

    public ClienteEntity(Long id, String nome, String cpf, String email, String numeroTelefone) {
        this.id = id;
        this.nome = nome;
        this.inativo = false;
        this.cpf = cpf;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
    }

    private boolean inativo;

    private String cpf;
    private String email;
    private String numeroTelefone;
    public boolean getInativo(){
        return this.inativo;
    }


}
