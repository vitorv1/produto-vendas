package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.entities.VendaEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public abstract class VendaMapper{

    public static VendaEntity paraEntity(Venda venda) {
        return VendaEntity.builder().
                id(venda.getId()).
                clienteEntity(ClienteMapper.paraEntity(venda.getCliente())).
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
                .listaProdutos(ProdutoMapper.paraDomainsDeDtos(vendaDto.listaProdutos()))
                .build();
    }


    public static VendaDto paraDtoDeDomain(Venda venda) {
        return VendaDto.builder()
                .id(venda.getId())
                .idCliente(venda.getIdCliente())
                .cliente(ClienteMapper.paraDtoDeDomain(venda.getCliente()))
                .valor(venda.getValor())
                .desconto(venda.getDesconto())
                .listaProdutos(ProdutoMapper.paraDtosDeDomains(venda.getListaProdutos()))
                .dataVenda(formataData(venda.getDataVenda()))
                .build();
    }

    public static List<VendaDto> paraDtosDeDomains(List<Venda> vendaList) {
        return vendaList.stream().map(VendaMapper::paraDtoDeDomain).toList();
    }

    public static List<Venda> paraDomains(List<VendaEntity> vendaEntities) {
        return vendaEntities.stream().map(VendaMapper::paraDomain).toList();
    }

    private static String formataData(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
        return data.format(formatter);
    }
}
