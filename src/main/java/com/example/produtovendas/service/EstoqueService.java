package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataPovider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueDataPovider estoqueDataPovider;

    public void cadastroEstoque(Produto produto){
        Estoque estoque = new Estoque(null, produto.getQuantidade(), produto);
        estoqueDataPovider.salvar(estoque);
    }
}
