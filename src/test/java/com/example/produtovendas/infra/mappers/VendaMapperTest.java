package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.produtovendas.validators.Validators.validaVendaDomain;
import static com.example.produtovendas.validators.Validators.validaVendaEntity;

class VendaMapperTest {

    @Test
    void testaSeRetornaUmEntity() {
        VendaEntity vendaEntity = VendaMapper.paraEntity(Builders.builderVendaDomain().get(0));
        validaVendaEntity(vendaEntity, null);
    }

    @Test
    void testaSeRetornaUmDomain() {
        Venda venda = VendaMapper.paraDomain(Builders.builderVendaEntity().get(0));
        validaVendaDomain(venda, null);
    }

    @Test
    void testaSeRetornaUmaListaDeDomains() {
        List<Venda> vendaList = VendaMapper.paraDomains(Builders.builderVendaEntity());
        validaVendaDomain(vendaList.get(0), vendaList.get(1));
    }
}