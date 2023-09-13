package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
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
        validaCliente(clienteEntityList);
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        Cliente clienteTeste = Builders.builderClienteDomain();
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(clienteTeste);
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(clienteEntity);
        validaCliente(clienteEntities);
    }

    @Test
    void testeSeRetornaUmaListaDeClientesDomain() {
        List<Cliente> clientes = ClienteMapper.paraClientes(Builders.builderCliente());
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(0)));
        clienteEntities.add(ClienteMapper.paraEntity(clientes.get(1)));
        validaCliente(clienteEntities);
    }

    private void validaCliente(List<ClienteEntity>clienteEntities){
        for (int i = 0; i < clienteEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderCliente().get(i).getId(), clienteEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderCliente().get(i).getNome(), clienteEntities.get(i).getNome());
            Assertions.assertEquals(Builders.builderCliente().get(i).isInativo(), clienteEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderCliente().get(i).getCpf(), clienteEntities.get(i).getCpf());
            Assertions.assertEquals(Builders.builderCliente().get(i).getEmail(), clienteEntities.get(i).getEmail());
            Assertions.assertEquals(Builders.builderCliente().get(i).getNumeroTelefone(), clienteEntities.get(i).getNumeroTelefone());
        }
    }
}