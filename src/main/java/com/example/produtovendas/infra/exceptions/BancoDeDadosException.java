package com.example.produtovendas.infra.exceptions;

public class BancoDeDadosException extends RuntimeException {
    public BancoDeDadosException (String mensage){
        super(mensage);
    }
}
