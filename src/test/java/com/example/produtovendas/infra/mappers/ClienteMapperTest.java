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
<<<<<<< HEAD
        Validators.validaClienteDomain(cliente, null);
=======
        List<ClienteEntity> clienteEntityList = new ArrayList<>();
        clienteEntityList.add(ClienteMapper.paraEntity(cliente));
        Validators.validaClienteEntity(clienteEntityList);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        Cliente clienteTeste = Builders.builderClienteDomain().get(0);
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(clienteTeste);
<<<<<<< HEAD
        Validators.validaClienteEntity(clienteEntity, null);
=======
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(clienteEntity);
        Validators.validaClienteEntity(clienteEntities);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testeSeRetornaUmaListaDeClientesDomain() {
        List<Cliente> clientes = ClienteMapper.paraClientes(Builders.builderCliente());
<<<<<<< HEAD
        Validators.validaClienteDomain(clientes.get(0), clientes.get(1));
=======
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(0)));
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(1)));
        Validators.validaClienteEntity(clienteEntities);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }
}
