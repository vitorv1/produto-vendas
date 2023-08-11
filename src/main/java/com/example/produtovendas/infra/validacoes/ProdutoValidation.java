package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;

import javax.crypto.interfaces.PBEKey;
import java.util.List;
import java.util.Objects;

public class ProdutoValidation {

    public static boolean validaProduto(List<Produto> produtos, Produto produto){
        boolean nome = false;
        boolean marca = false;
        boolean valor = false;

        for(Produto produto1 : produtos){
            if(Objects.equals(produto1.getNome(), produto.getNome())){
                nome = true;
            }
            if(Objects.equals(produto1.getMarca(), produto.getMarca())){
                marca = true;
            }
            if(Objects.equals(produto1.getValor(), produto.getValor())){
                valor = true;
            }
        }
        if(nome && marca && valor){
            return true;
        }else{
            return false;
        }
    }
}
