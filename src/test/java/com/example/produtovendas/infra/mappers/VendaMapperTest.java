package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.produtovendas.validators.Validators.validaVendaEntity;

class VendaMapperTest {

    @Test
    void testaSeRetornaUmEntity() {
        VendaEntity vendaEntity = VendaMapper.paraEntity(Builders.builderVendaDomain().get(0));
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(vendaEntity);
        validaVendaEntity(vendaEntities);
    }

    @Test
    void testaSeRetornaUmDomain() {
        Venda venda = VendaMapper.paraDomain(Builders.builderVenda().get(0));
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(VendaMapper.paraEntity(venda));
        validaVendaEntity(vendaEntities);
    }

    @Test
    void testaSeRetornaUmaListaDeDomains() {
        List<Venda> vendaList = VendaMapper.paraDomains(Builders.builderVenda());
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(VendaMapper.paraEntity(vendaList.get(0)));
        vendaEntities.add(VendaMapper.paraEntity(vendaList.get(1)));
        validaVendaEntity(vendaEntities);
    }
}