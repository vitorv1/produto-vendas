package com.example.produtovendas.domain;

import com.example.produtovendas.entity.ClienteEntity;
import lombok.*;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Clientes {

    private String nome;
    private String cpf;
    private String email;
    private String numero_telefone;

    public Clientes(ClienteEntity clienteEntity){
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.email = clienteEntity.getEmail();
        this.numero_telefone = clienteEntity.getNumero_telefone();
    }
}
