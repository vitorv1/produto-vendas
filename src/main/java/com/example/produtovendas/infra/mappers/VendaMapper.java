package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;

import java.util.List;

public class VendaMapper {

    public static VendaEntity paraEntity(Venda venda) {
        return VendaEntity.builder().
                id(venda.getId()).
                clienteEntity(ClienteMapper.paraEntity(venda.getCliente())).
                inativo(venda.isInativo()).
                valor(venda.getValor()).
                desconto(venda.getDesconto()).
                listaProdutos(ProdutoMapper.paraEntitys(venda.getListaProdutos())).
                dataVenda(venda.getDataVenda()).
                build();
    }

    public static Venda paraDomain(VendaEntity vendaEntity) {
        return Venda.builder().
                id(vendaEntity.getId()).
                cliente(ClienteMapper.paraCliente(vendaEntity.getClienteEntity())).
                idCliente(vendaEntity.getClienteEntity().getId()).
                inativo(vendaEntity.isInativo()).
                valor(vendaEntity.getValor()).
                desconto(vendaEntity.getDesconto()).
                listaProdutos(ProdutoMapper.paraProdutos(vendaEntity.getListaProdutos())).
                dataVenda(vendaEntity.getDataVenda()).
                build();
    }

    public static List<Venda> paraDomains(List<VendaEntity> vendaEntities) {
        return vendaEntities.stream().map(VendaMapper::paraDomain).toList();
    }
}
