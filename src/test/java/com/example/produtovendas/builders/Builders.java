package com.example.produtovendas.builders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.EstoqueEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.mappers.VendaMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Builders {

    public static List<ProdutoEntity> builderProdutoEntity(){
        List<ProdutoEntity> produtoEntityList = new ArrayList<>();
        produtoEntityList.add(new ProdutoEntity(1L, "Tenis", false, "Nike", new BigDecimal(320), 0));
        produtoEntityList.add(new ProdutoEntity(3L ,"Camiseta", false, "Puma", new BigDecimal(400), 0));
        return produtoEntityList;
    }

    public static List<Optional<ProdutoEntity>> builderProdutoOptional(){
        List<Optional<ProdutoEntity>> produtoEntityList = new ArrayList<>();
        produtoEntityList.add(Optional.of(builderProdutoEntity().get(0)));
        produtoEntityList.add(Optional.of(builderProdutoEntity().get(1)));
        return produtoEntityList;
    }
    public static List<ClienteEntity> builderClienteEntity(){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(new ClienteEntity(1L, "Mariana", false, "456357159-17", "email@gmail.com", "(44)99874-8356"));
        clienteEntities.add(new ClienteEntity(3L, "João", false, "789456123-55", "email@gmail.com", "(44)98747-5623"));
        return clienteEntities;
    }
    public static List<Optional<ClienteEntity>> builderClienteOptional(){
        List<Optional<ClienteEntity>> clienteEntityList = new ArrayList<>();
        clienteEntityList.add(Optional.of(builderClienteEntity().get(0)));
        clienteEntityList.add(Optional.of(builderClienteEntity().get(1)));
        return clienteEntityList;
    }

    public static List<Optional<VendaEntity>> builderVendaOptional(){
        List<Optional<VendaEntity>> vendaEntities = new ArrayList<>();
        vendaEntities.add(Optional.of(builderVendaEntity().get(0)));
        vendaEntities.add(Optional.of(builderVendaEntity().get(1)));
        return vendaEntities;
    }

    public static List<VendaEntity> builderVendaEntity(){
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(new VendaEntity(2L, Builders.builderClienteEntity().get(0), false, new BigDecimal(648), 10, builderProdutoEntity(), LocalDate.now()));
        vendaEntities.add(new VendaEntity(7L, Builders.builderClienteEntity().get(1), false, new BigDecimal(720), 0, builderProdutoEntity(), LocalDate.now()));
        return vendaEntities;
    }

    public static List<Cliente> builderClienteDomain(){
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(ClienteMapper.paraCliente(Builders.builderClienteEntity().get(0)));
        clienteList.add(ClienteMapper.paraCliente(Builders.builderClienteEntity().get(1)));
        return clienteList;
    }

    public static List<Produto> builderProdutoDomain(){
        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(ProdutoMapper.paraProduto(Builders.builderProdutoEntity().get(0)));
        produtoList.add(ProdutoMapper.paraProduto(Builders.builderProdutoEntity().get(1)));
        return produtoList;
    }

    public static List<Venda> builderVendaDomain() {
        List<Venda> vendaList = new ArrayList<>();
        vendaList.add(VendaMapper.paraDomain(Builders.builderVendaEntity().get(0)));
        vendaList.add(VendaMapper.paraDomain(Builders.builderVendaEntity().get(1)));
        return vendaList;
    }

    public static Venda builderVendaDomainDto(int index){
        Venda venda = builderVendaDomain().get(index);
        venda.setId(null);
        venda.setCliente(null);
        venda.setDataVenda(null);
        return venda;
    }

    public static EstoqueEntity builderEstoqueEntity(){
        ProdutoEntity produtoEntity = builderProdutoEntity().get(0);
        return new EstoqueEntity(produtoEntity.getId(), produtoEntity.getQuantidade(), produtoEntity);
    }

    public static String builderJsonVenda(){
        return "{\"idCliente\":12,\"desconto\":10,\"listaProdutos\":[{\"id\":3},{\"id\":7}]}";
    }

    public static String builderJsonProduto(){
        return "{\"nome\": \"Tenis\", \"marca\":\"Nike\", \"valor\": 320}";
    }

    public static String builderJsonCliente(){
        return "{\"nome\": \"Mariana\", \"cpf\":\"456357159-17\", \"email\": \"email@gmail.com\", \"numeroTelefone\": \"(44)99874-8356\"}";
    }
}
