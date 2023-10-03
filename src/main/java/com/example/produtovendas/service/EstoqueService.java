package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.EstoqueDataPovider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueDataPovider estoqueDataPovider;
    private final ProdutoDataProvider produtoDataProvider;

    public void cadastroEstoque(Produto produto){
        Estoque estoque = new Estoque(null, produto.getQuantidade(), produto);
        estoqueDataPovider.salvar(estoque);
    }

    public void definirQuantidadeEstoque(Venda venda, List<Produto> produtoList){
        for (int i = 0; i < produtoList.size(); i++) {
            produtoList.get(i).ajustaQuantidade(venda.getListaProdutos().get(i).getQuantidade());
        }
        produtoList.forEach(produtoDataProvider::salvar);
    }
}
