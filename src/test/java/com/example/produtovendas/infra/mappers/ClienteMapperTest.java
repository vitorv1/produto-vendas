package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    @Test
    void testaSeRetornaUmClienteDomain() {
        Cliente cliente = new Cliente(4L, "Vitor", false, "12345678911", "vivi@gmail.com", "(44)99874-8356");
        Assertions.assertEquals(cliente, ClienteMapper.paraCliente(
                new ClienteEntity(4L, "Vitor", false, "12345678911", "vivi@gmail.com", "(44)99874-8356")));
    }

    @Test
    void paraEntity() {
    }

    @Test
    void paraClientes() {
    }
}