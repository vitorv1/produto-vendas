package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    @Test
    void testaSeRetornaUmClienteDomain() {
      String nome = "Vitor";
      Long id = 5L;
      boolean inativo = false;
      String cpf = "123456789-11";
      String email = "vivi@gmail.com";
      String numeroTelefone = "(44)998748356";

      Cliente cliente = ClienteMapper.paraCliente(new ClienteEntity(id, nome, inativo, cpf, email, numeroTelefone));
      Assertions.assertEquals(nome, cliente.getNome());
      Assertions.assertEquals(id, cliente.getId());
      Assertions.assertEquals(inativo, cliente.isInativo());
      Assertions.assertEquals(cpf, cliente.getCpf());
      Assertions.assertEquals(email, cliente.getEmail());
      Assertions.assertEquals(numeroTelefone, cliente.getNumeroTelefone());
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        String nome = "Vitor";
        Long id = 5L;
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vivi@gmail.com";
        String numeroTelefone = "(44)998748356";

        ClienteEntity clienteEntity = ClienteMapper.paraEntity(new Cliente(id, nome, inativo, cpf, email, numeroTelefone));
        Assertions.assertEquals(nome, clienteEntity.getNome());
        Assertions.assertEquals(id, clienteEntity.getId());
        Assertions.assertEquals(inativo, clienteEntity.isInativo());
        Assertions.assertEquals(cpf, clienteEntity.getCpf());
        Assertions.assertEquals(email, clienteEntity.getEmail());
        Assertions.assertEquals(numeroTelefone, clienteEntity.getNumeroTelefone());
    }

    @Test
    void testeSeRetornaUmaListaDeClientesDomain() {
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        String nome1 = "Vitor";
        Long id1 = 5L;
        boolean inativo1 = false;
        String cpf1 = "123456789-11";
        String email1 = "vivi@gmail.com";
        String numeroTelefone1 = "(44)998748356";

        String nome2 = "Daniel";
        Long id2 = 4L;
        boolean inativo2 = false;
        String cpf2 = "123456789-22";
        String email2 = "francis@gmail.com";
        String numeroTelefone2 = "(44)998748346";

        clienteEntities.add(new ClienteEntity(id1, nome1, inativo1, cpf1, email1, numeroTelefone1));
        clienteEntities.add(new ClienteEntity(id2, nome2, inativo2, cpf2, email2, numeroTelefone2));

        List<Cliente> clientes = ClienteMapper.paraClientes(clienteEntities);

        Cliente cliente1 = clientes.get(0);
        Cliente cliente2 = clientes.get(1);

        Assertions.assertEquals(nome1, cliente1.getNome());
        Assertions.assertEquals(id1, cliente1.getId());
        Assertions.assertEquals(inativo1, cliente1.isInativo());
        Assertions.assertEquals(cpf1, cliente1.getCpf());
        Assertions.assertEquals(email1, cliente1.getEmail());
        Assertions.assertEquals(numeroTelefone1, cliente1.getNumeroTelefone());

        Assertions.assertEquals(nome2, cliente2.getNome());
        Assertions.assertEquals(id2, cliente2.getId());
        Assertions.assertEquals(inativo2, cliente2.isInativo());
        Assertions.assertEquals(cpf2, cliente2.getCpf());
        Assertions.assertEquals(email2, cliente2.getEmail());
        Assertions.assertEquals(numeroTelefone2, cliente2.getNumeroTelefone());
    }
}