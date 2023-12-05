package com.example.produtovendas.infra.validacoes;

public class ValidationNull<A>{
    public boolean validationAtribute(A atribute){
        if(atribute != null){
            return true;
        }else {
            return false;
        }
    }
}
