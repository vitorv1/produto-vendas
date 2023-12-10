package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.dtos.ClienteDto;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ClienteMapperTest {

    List<ClienteEntity> clienteEntityList = new ArrayList<>();

    @Test
    void testaSeRetornaUmClienteDomain() {
        Cliente cliente = ClienteMapper.paraDomain(Builders.builderClienteEntity().get(0));
        Validators.validaClienteDomain(cliente, 0);
    }

    @Test
    void testaSeRetornaUmClienteEntity() {
        Cliente clienteTeste = Builders.builderClienteDomain().get(0);
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(clienteTeste);
        Validators.validaClienteEntity(clienteEntity, null);
    }

    @Test
    void testaSeRetornaUmaListaDeClientesDomain() {
        List<Cliente> clientes = ClienteMapper.paraDomains(Builders.builderClienteEntity());
        Validators.validaClienteDomain(clientes.get(0), 0);
        Validators.validaClienteDomain(clientes.get(1), 1);
    }

    @Test
    void testaSeRetornaUmClienteDtoDeUmDomain(){
        Cliente clienteTeste = Builders.builderClienteDomain().get(0);
        ClienteDto clienteDto = ClienteMapper.paraDtoDeDomain(clienteTeste);
        Validators.validaClienteDto(clienteDto, 0);
    }

    @Test
    void testaSeRetornaClienteDomainDeDto(){
        ClienteDto clienteDto = Builders.builderClienteDto().get(0);
        Cliente cliente = ClienteMapper.paraDomainDeDto(clienteDto);
        Validators.validaClienteDomain(cliente, 0);
    }

    @Test
    void testaSeRetornaUmaListaDeCLientesDtosDeUmaListaDeDomais(){
        List<Cliente> clienteList = Builders.builderClienteDomain();
        List<ClienteDto> clienteDtos = ClienteMapper.paraDtosDeDomains(clienteList);
        Validators.validaClienteDto(clienteDtos.get(0), 0);
        Validators.validaClienteDto(clienteDtos.get(1), 1);
    }
}
