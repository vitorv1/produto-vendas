package com.example.produtovendas.entity;

import com.example.produtovendas.dto.Cliente;
import jakarta.persistence.*;
import lombok.*;


@Entity(name="Cliente")
@Table(name="clientes")
@EqualsAndHashCode(of="id")
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
    private String numero_telefone;

    public ClienteEntity (Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.numero_telefone = cliente.getNumero_telefone();
    }


    public static ClienteEntity atualizaDados(Cliente cliente){
        return new ClienteEntity(cliente);
    }
}
