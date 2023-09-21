package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ClienteMapperTest {

    List<ClienteEntity> clienteEntityList = new ArrayList<>();

    @Test
    void testaSeRetornaUmClienteDomain() {
        Cliente cliente = ClienteMapper.paraCliente(Builders.builderClienteEntity().get(0));
        Validators.validaClienteDomain(cliente, 0);
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        Cliente clienteTeste = Builders.builderClienteDomain().get(0);
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(clienteTeste);
        Validators.validaClienteEntity(clienteEntity, null);
    }

    @Test
    void testeSeRetornaUmaListaDeClientesDomain() {
        List<Cliente> clientes = ClienteMapper.paraClientes(Builders.builderClienteEntity());
        Validators.validaClienteDomain(clientes.get(0), 0);
        Validators.validaClienteDomain(clientes.get(1), 1);
    }
}
