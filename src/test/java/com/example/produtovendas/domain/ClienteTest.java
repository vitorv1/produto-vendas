package com.example.produtovendas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void inativar() {
        Cliente cliente = new Cliente(1L, "Vitor", false, "123456789-77", "vivi@gmail.com", "(44)99874-8356");
        cliente.inativar();
        Assertions.assertTrue(cliente.isInativo());
    }

    @Test
    void atualizarDados() {
        Cliente cliente = new Cliente(1L, "Vitor", false, "123456789-77", "vivi@gmail.com", "(44)99874-8356");



    }
}