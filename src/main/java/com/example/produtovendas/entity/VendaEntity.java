package com.example.produtovendas.entity;


import ch.qos.logback.core.net.server.Client;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.repository.ClienteRepository;
import com.example.produtovendas.service.ClienteService;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "venda")
@Table(name = "vendas")
@EqualsAndHashCode(of = "id")
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_cliente", nullable = false)
    private ClienteEntity clienteEntity;
    private double valor;
    private Integer desconto;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="venda_produtos",
    joinColumns = {@JoinColumn(name="id_venda")},
            inverseJoinColumns = {@JoinColumn(name = "id_produtp")}
    )
    private List<ProdutoEntity> listaProdutos;
    private LocalDate dataVenda;


    public VendaEntity (ClienteEntity clienteEntity, double valor, Integer desconto, List<ProdutoEntity> listaProdutos, LocalDate dataVenda){
        this.clienteEntity = clienteEntity;
        this.valor = valor;
        this.desconto = desconto;
        this.listaProdutos = listaProdutos;
        this.dataVenda = dataVenda;
    }

    public void alterarDados(Long idCliente, Integer desconto, List<Produto> produtoList){
        ClienteRepository clienteRepository = null;
        this.clienteEntity = clienteRepository.getReferenceById(idCliente);
        this.desconto = desconto;
        this.listaProdutos = ProdutoMapper.paraEntitys(produtoList);
    }

}
