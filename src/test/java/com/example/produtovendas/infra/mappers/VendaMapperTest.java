package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import static com.example.produtovendas.validators.Validators.validaVendaDomain;
=======
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import static com.example.produtovendas.validators.Validators.validaVendaEntity;

class VendaMapperTest {

    @Test
    void testaSeRetornaUmEntity() {
        VendaEntity vendaEntity = VendaMapper.paraEntity(Builders.builderVendaDomain().get(0));
<<<<<<< HEAD
        validaVendaEntity(vendaEntity, null);
=======
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(vendaEntity);
        validaVendaEntity(vendaEntities);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testaSeRetornaUmDomain() {
        Venda venda = VendaMapper.paraDomain(Builders.builderVenda().get(0));
<<<<<<< HEAD
        validaVendaDomain(venda, null);
=======
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(VendaMapper.paraEntity(venda));
        validaVendaEntity(vendaEntities);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testaSeRetornaUmaListaDeDomains() {
        List<Venda> vendaList = VendaMapper.paraDomains(Builders.builderVenda());
<<<<<<< HEAD
        validaVendaDomain(vendaList.get(0), vendaList.get(1));
=======
        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(VendaMapper.paraEntity(vendaList.get(0)));
        vendaEntities.add(VendaMapper.paraEntity(vendaList.get(1)));
        validaVendaEntity(vendaEntities);
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }
}