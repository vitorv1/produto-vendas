package com.example.produtovendas.validators;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public abstract class Validators {

    public static void validaClienteEntity(List<ClienteEntity> clienteEntities){
        for (int i = 0; i < clienteEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderCliente().get(i).getId(), clienteEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderCliente().get(i).getNome(), clienteEntities.get(i).getNome());
            Assertions.assertEquals(Builders.builderCliente().get(i).isInativo(), clienteEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderCliente().get(i).getCpf(), clienteEntities.get(i).getCpf());
            Assertions.assertEquals(Builders.builderCliente().get(i).getEmail(), clienteEntities.get(i).getEmail());
            Assertions.assertEquals(Builders.builderCliente().get(i).getNumeroTelefone(), clienteEntities.get(i).getNumeroTelefone());
        }
    }

    public static void validaProdutoEntity(List<ProdutoEntity> produtoEntities){
        for (int i = 0; i < produtoEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderProduto().get(i).getId(), produtoEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderProduto().get(i).getNome(), produtoEntities.get(i).getNome());
            Assertions.assertFalse(produtoEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderProduto().get(i).getMarca(), produtoEntities.get(i).getMarca());
            Assertions.assertEquals(Builders.builderProduto().get(i).getValor(), produtoEntities.get(i).getValor());
        }
    }

    public static void validaVendaEntity(List<VendaEntity> vendaEntities){
        for (int i = 0; i < vendaEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderVenda().get(i).getId(), vendaEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderVenda().get(i).getClienteEntity(), vendaEntities.get(i).getClienteEntity());
            Assertions.assertFalse(vendaEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderVenda().get(i).getValor(), vendaEntities.get(i).getValor());
            Assertions.assertEquals(Builders.builderVenda().get(i).getDesconto(), vendaEntities.get(i).getDesconto());
            Assertions.assertEquals(Builders.builderVenda().get(i).getListaProdutos(), vendaEntities.get(i).getListaProdutos());
            Assertions.assertEquals(Builders.builderVenda().get(i).getDataVenda(), vendaEntities.get(i).getDataVenda());
        }
    }

    public static void validaClienteDomain(Cliente cliente1, Cliente cliente2){
        List<Cliente> clienteList = new ArrayList<>();
        if(cliente1 != null) {
            clienteList.add(cliente1);
        }
        if(cliente2 != null){
            clienteList.add(cliente2);
        }
        for (int i = 0; i < clienteList.size(); i++) {
            System.out.println(i);
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getId(), clienteList.get(i).getId());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getNome(), clienteList.get(i).getNome());
            Assertions.assertFalse(clienteList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getCpf(), clienteList.get(i).getCpf());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getEmail(), clienteList.get(i).getEmail());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getNumeroTelefone(), clienteList.get(i).getNumeroTelefone());
        }
    }

    public static void validaProdutoDomain(Produto produto1, Produto produto2){
        List<Produto> produtoList = new ArrayList<>();
        if(produto1 != null){
            produtoList.add(produto1);
        }
        if(produto2 != null){
            produtoList.add(produto2);
        }

        for (int i = 0; i < produtoList.size(); i++) {
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getId(), produtoList.get(i).getId());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getNome(), produtoList.get(i).getNome());
            Assertions.assertFalse(produtoList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getMarca(), produtoList.get(i).getMarca());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getValor(), produtoList.get(i).getValor());
        }
    }

    public static void validaVendaDomain(Venda venda1, Venda venda2){
        List<Venda> vendaList = new ArrayList<>();

        if(venda1 != null){
            vendaList.add(venda1);
        }
        if(venda2 != null){
            vendaList.add(venda2);
        }

        for (int i = 0; i < vendaList.size(); i++) {
            if(vendaList.get(i).getId() == null){
                Assertions.assertNull(vendaList.get(i).getId());
            }else{
                Assertions.assertEquals(Builders.builderVendaDomain().get(i).getId(), vendaList.get(i).getId());
            }
            validaClienteDomain(vendaList.get(i).getCliente(), null);
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getValor(), vendaList.get(i).getValor());
            Assertions.assertFalse(vendaList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getDesconto(), vendaList.get(i).getDesconto());
            validaProdutoEntity(ProdutoMapper.paraEntitys(vendaList.get(i).getListaProdutos()));
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getDataVenda(), vendaList.get(i).getDataVenda());
        }
    }
}
