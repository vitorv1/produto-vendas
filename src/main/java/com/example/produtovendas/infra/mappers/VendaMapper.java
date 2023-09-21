package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VendaMapper {

    public static VendaEntity paraEntity(Venda venda) {
        return new VendaEntity(venda.getId(), ClienteMapper.paraEntity(venda.getCliente()), venda.isInativo(), venda.getValor(), venda.getDesconto(), ProdutoMapper.paraEntitys(venda.getListaProdutos()), venda.getDataVenda());
    }

    public static Venda paraDomain(VendaEntity vendaEntity) {
        return new Venda(vendaEntity.getId(), ClienteMapper.paraCliente(vendaEntity.getClienteEntity()), vendaEntity.getClienteEntity().getId(), vendaEntity.getValor(), vendaEntity.isInativo(), vendaEntity.getDesconto(), ProdutoMapper.paraProdutos(vendaEntity.getListaProdutos()), vendaEntity.getDataVenda());
    }

    public static List<Venda> paraDomains(List<VendaEntity> vendaEntities) {
        return vendaEntities.stream().map(VendaMapper::paraDomain).toList();
    }
}
