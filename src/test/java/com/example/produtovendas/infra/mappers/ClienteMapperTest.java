package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ClienteMapperTest {

    List<ClienteEntity> clienteEntityList = new ArrayList<>();
    @Test
    void testaSeRetornaUmClienteDomain() {
        Cliente cliente = ClienteMapper.paraCliente(Builders.builderCliente().get(0));
        List<ClienteEntity> clienteEntityList = new ArrayList<>();
        clienteEntityList.add(ClienteMapper.paraEntity(cliente));
        Validators.validaClienteEntity(clienteEntityList);
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        Cliente clienteTeste = Builders.builderClienteDomain().get(0);
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(clienteTeste);
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(clienteEntity);
        Validators.validaClienteEntity(clienteEntities);
    }

    @Test
    void testeSeRetornaUmaListaDeClientesDomain() {
        List<Cliente> clientes = ClienteMapper.paraClientes(Builders.builderCliente());
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(0)));
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(1)));
        Validators.validaClienteEntity(clienteEntities);
    }
}