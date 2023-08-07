package com.example.produtovendas.infra;

import java.sql.SQLException;

public class BancoDeDadosException extends SQLException {
    public BancoDeDadosException (String mensage){
        super(mensage);
    }
}
