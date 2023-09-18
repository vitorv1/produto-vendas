package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void testeMetodoInativar() {
        Venda venda = Builders.builderVendaDomain().get(0);
        venda.inativar();
        Assertions.assertTrue(venda.isInativo());
    }

    @Test
    void atualizaDados() {
        Venda venda = Builders.builderVendaDomain().get(0);

        Venda vendaDto = Builders.builderVendaDomain().get(1);
        vendaDto.setId(null);

        venda.atualizaDados(vendaDto);

        Validators.validaVendaDomain(venda, 1);
    }
}