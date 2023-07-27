package com.example.produtovendas.entity;

import com.example.produtovendas.domain.Venda;

public class VendaMapper {

    public static VendaEntity paraEntity(Venda venda){
        return new VendaEntity(venda.getCliente(), venda.getValor(), venda.getDesconto(), venda.getListaProdutos(), venda.getDataVenda());
    }
}
