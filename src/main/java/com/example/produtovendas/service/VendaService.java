package com.example.produtovendas.service;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaDataProvider vendaDataProvider;

    public Venda cadastroVenda(Venda venda){
        return vendaDataProvider.cadastroVenda(venda);
    }

    public Venda buscarPorId(Long id){
        return vendaDataProvider.buscarPorId(id);
    }

    public List<Venda> buscarTodos(){
        return vendaDataProvider.buscarTodos();
    }

    public void deletarVenda(Long id){
       vendaDataProvider.deletarVenda(id);
    }

    public Venda alterarVenda(Long id, Venda vendaDto){
        Venda venda = vendaDataProvider.buscarPorId(id);
        venda.atualizaDados(vendaDto.getId(), vendaDto.getCliente(), vendaDto.getIdCliente(), vendaDto.getValor(), vendaDto.isInativo(), vendaDto.getDesconto(), vendaDto.getListaProdutos(), vendaDto.getDataVenda());
        return vendaDataProvider.cadastroVenda(venda);
    }
}
