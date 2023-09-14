package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testeMetodoInativar() {
        Cliente cliente = Builders.builderClienteDomain().get(0);
        cliente.inativar();
        Assertions.assertTrue(cliente.isInativo());
    }

    @Test
    void testeMetodoAtualizarDados() {
        Long id = 1L;
        String nome = "Daniel";
        String cpf = "789456123-99";
        String email = "francis@gmail.com";
        String numeroTelefone = "(44)99875-5623";
        Cliente cliente = Builders.builderClienteDomain().get(0);
        Cliente clienteDto = new Cliente(null, nome, false, cpf, email, numeroTelefone);

        cliente.atualizarDados(clienteDto);

        Assertions.assertEquals(id, cliente.getId());
        Assertions.assertEquals(nome, cliente.getNome());
        Assertions.assertFalse(cliente.isInativo());
        Assertions.assertEquals(cpf, cliente.getCpf());
        Assertions.assertEquals(email, cliente.getEmail());
        Assertions.assertEquals(numeroTelefone, cliente.getNumeroTelefone());
    }
}