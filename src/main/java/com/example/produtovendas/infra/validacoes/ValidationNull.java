package com.example.produtovendas.infra.validacoes;

public class ValidationNull<A>{
    public boolean validationAtribute(A atribute){
        return atribute != null;
    }
}
