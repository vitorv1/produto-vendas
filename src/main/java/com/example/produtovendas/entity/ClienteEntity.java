package com.example.produtovendas.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Cliente")
@Table(name = "clientes")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String numeroTelefone;

    public ClienteEntity(String nome, String cpf, String email, String numero_telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.numeroTelefone = numero_telefone;
    }

    public void atualizaDados(String nome, String cpf, String email, String numeroTelefone){
        this.nome = nome;
        this.cpf= cpf;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
    }
}
