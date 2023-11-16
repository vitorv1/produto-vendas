package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static com.example.produtovendas.validators.Validators.validaVendaDomain;
//import static com.example.produtovendas.validators.Validators.validaVendaEntity;

class VendaMapperTest {

   /* @Test
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
    }*/
}