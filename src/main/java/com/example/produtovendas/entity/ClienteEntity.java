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
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean inativo;
    private String cpf;
    private String email;
    private String numeroTelefone;


    public void atualizaDados(String nome, String cpf, String email, String numeroTelefone) {
        this.inativo = false;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
    }

    public boolean getInativo(){
        return this.inativo;
    }

}
