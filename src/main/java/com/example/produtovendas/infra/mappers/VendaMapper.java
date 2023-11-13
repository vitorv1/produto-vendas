package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.entities.VendaEntity;

import java.util.List;

public abstract class VendaMapper{

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
                cliente(ClienteMapper.paraDomain(vendaEntity.getClienteEntity())).
                idCliente(vendaEntity.getClienteEntity().getId()).
                inativo(vendaEntity.isInativo()).
                valor(vendaEntity.getValor()).
                desconto(vendaEntity.getDesconto()).
                listaProdutos(ProdutoMapper.paraDomains(vendaEntity.getListaProdutos())).
                dataVenda(vendaEntity.getDataVenda()).
                build();
    }


    public static Venda paraDomainDeDto(VendaDto vendaDto) {
        return Venda.builder()
                .idCliente(vendaDto.idCliente())
                .desconto(vendaDto.desconto())
                .listaProdutos(vendaDto.listaProdutos())
                .build();
    }


    public static VendaDto paraDtoDeDomain(Venda venda) {
        return VendaDto.builder()
                .id(venda.getId())
                .idCliente(venda.getIdCliente())
                .valor(venda.getValor())
                .inativo(venda.isInativo())
                .desconto(venda.getDesconto())
                .listaProdutos(venda.getListaProdutos())
                .dataVenda(venda.getDataVenda())
                .build();
    }

    public static List<VendaDto> paraDtosDeDomains(List<Venda> vendaList) {
        return vendaList.stream().map(VendaMapper::paraDtoDeDomain).toList();
    }

    public static List<Venda> paraDomains(List<VendaEntity> vendaEntities) {
        return vendaEntities.stream().map(VendaMapper::paraDomain).toList();
    }
}
