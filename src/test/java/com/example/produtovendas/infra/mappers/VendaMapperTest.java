package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.example.produtovendas.validators.Validators.validaVendaDomain;
import static com.example.produtovendas.validators.Validators.validaVendaEntity;

class VendaMapperTest {

    @Test
    void testaSeRetornaUmEntity() {
        VendaEntity vendaEntity = VendaMapper.paraEntity(Builders.builderVendaDomain().get(0));
        validaVendaEntity(vendaEntity, 0);
    }

    @Test
    void testaSeRetornaUmDomain() {
        Venda venda = VendaMapper.paraDomain(Builders.builderVendaEntity().get(0));
        validaVendaDomain(venda, 0);
    }

    @Test
    void testaSeRetornaUmaListaDeDomains() {
        List<Venda> vendaList = VendaMapper.paraDomains(Builders.builderVendaEntity());
        validaVendaDomain(vendaList.get(0), 0);
        validaVendaDomain(vendaList.get(1), 1);
    }

    @Test
    void testaSeRetonraUmaVendaDtoDeUmDomain(){
        Venda venda = Builders.builderVendaDomain().get(0);
        VendaDto vendaTeste = VendaMapper.paraDtoDeDomain(venda);
        Validators.validaVendaDto(vendaTeste, 0);
    }

    @Test
    void testaSeRetornaUmaVendaDomainDeUmDto(){
        VendaDto vendaDto = Builders.builderVendaDto().get(0);
        Venda vendaTeste = VendaMapper.paraDomainDeDto(vendaDto);

        Assertions.assertEquals(vendaTeste.getIdCliente(), vendaDto.idCliente());
        Assertions.assertEquals(vendaTeste.getDesconto(), vendaDto.desconto());
        Assertions.assertEquals(vendaTeste.getListaProdutos().get(0).getId(), vendaDto.listaProdutos().get(0).id());
        Assertions.assertEquals(vendaTeste.getListaProdutos().get(1).getId(), vendaDto.listaProdutos().get(1).id());
    }

    @Test
    void testaSeRetornaUmaListaDeVendasDtosDeUmaListaDeDomains(){
        List<Venda> vendaList = Builders.builderVendaDomain();
        List<VendaDto> listTeste = VendaMapper.paraDtosDeDomains(vendaList);
        Validators.validaVendaDto(listTeste.get(0), 0);
        Validators.validaVendaDto(listTeste.get(1), 1);
    }
}