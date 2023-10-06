package com.example.produtovendas.service;

import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.EstoqueDataPovider;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueDataPovider estoqueDataPovider;
    private final ProdutoDataProvider produtoDataProvider;

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque(null, produto.getQuantidade(), produto);
        estoqueDataPovider.salvar(estoque);
    }

    public List<Produto> definirQuantidadeEstoque(List<Produto> produtoList){
        List<Produto> produtoListRepitidos = new ArrayList<>();
        for (int i = 0; i < produtoList.size(); i++) {
            Long id = produtoList.get(i).getId();
            for (int j = i + 1; j < produtoList.size(); j++) {
                if(Objects.equals(produtoList.get(j).getId(), id)){
                    produtoListRepitidos.add(produtoList.get(i));
                }
            }
        }
        if(!produtoListRepitidos.isEmpty()){
            List<Produto> produtosCertos = new ArrayList<>();
            for (int i = 0; i < produtoListRepitidos.size(); i++) {
                int repitido = 1;
               Long id = produtoList.get(i).getId();
                for (int j = i + 1; j < produtoListRepitidos.size(); j++) {
                    if (Objects.equals(produtoList.get(j).getId(), id)){
                       repitido ++;
                    }else {
                          Produto produto = produtoListRepitidos.get(i);
                          produto.setQuantidade(produto.getQuantidade() - (repitido + 1));
                          produtoDataProvider.salvar(produto);
                          produtoList.forEach(produto1 -> produtosCertos.add(produtoDataProvider.consultarPorId(produto1.getId())));
                          return produtosCertos;
                    }
                }
            }
        }


        return produtoList.stream().map(this::ajustaQuantidade).collect(Collectors.toList());
    }

    public Produto ajustaQuantidade(Produto produto){
        produto.setQuantidade(produto.getQuantidade() - 1);
        return produtoDataProvider.salvar(produto);
    }
}
