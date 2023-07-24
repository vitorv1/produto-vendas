package com.example.produtovendas.entity;

import com.example.produtovendas.dto.Produto;
import jakarta.persistence.*;
import lombok.*;


@Entity(name="Produto")
@Table(name="produtos")
@EqualsAndHashCode(of="id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private double valor;

    public ProdutoEntity (Produto produto){
        this.nome = produto.getNome();
        this.marca = produto.getMarca();
        this.valor = produto.getValor();
    }

    public static ProdutoEntity atualizaDados(Produto produto){
        return new ProdutoEntity(produto);
    }
}
