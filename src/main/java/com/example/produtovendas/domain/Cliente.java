package com.example.produtovendas.domain;

import jakarta.persistence.*;


@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String numero_telefone;


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", numero_telefone='" + numero_telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumero_telefone() {
        return numero_telefone;
    }

    public String getEmail() {
        return email;
    }

    public Cliente(String nome, String cpf, String numero_telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero_telefone = numero_telefone;
        this.email = email;
    }


}
