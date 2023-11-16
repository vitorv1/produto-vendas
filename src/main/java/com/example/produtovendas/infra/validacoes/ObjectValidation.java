package com.example.produtovendas.infra.validacoes;

public class ObjectValidation {
    public static boolean validaObjeto(Object object){
        if(object != null){
            return true;
        }else{
            return false;
        }
    }
}
