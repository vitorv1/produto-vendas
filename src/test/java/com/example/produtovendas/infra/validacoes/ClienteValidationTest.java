package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ClienteValidationTest {

    @Test
    void testarSeValidacaoEstaLancandoException() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1L, "Vitor", false, "12345678922", "vivih@gmail.com", "(44)99874-8356"));
        clientes.add(new Cliente(2L, "Heitor", false, "12345678923", "heitorgomes@gmail.com", "(44)99874-8355"));

        Assertions.assertThrows(RuntimeException.class, ()-> ClienteValidation.validaCliente(clientes,
                new Cliente(3L, "Gustavo", false, "12345678922", "gusnakamura@gmail,com", "(44)89787-8788")));
    }
}