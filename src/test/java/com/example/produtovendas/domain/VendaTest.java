package com.example.produtovendas.domain;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void testeMetodoAtualizaDados() {
        Venda venda = Builders.builderVendaDomain().get(0);

        Venda vendaDto = Builders.builderVendaDomain().get(1);
        vendaDto.setId(null);

        venda.atualizaDados(vendaDto);

        Validators.validaVendaDomain(venda, 1);
    }

    @Test
    void testeMetodoCalcularValorVendaComDesconto(){
        Venda venda = Builders.builderVendaDomain().get(0);
        venda.setValor(new BigDecimal(0));

        venda.calcularValorVenda();

        Assertions.assertEquals(648.0, venda.getValor().doubleValue());
    }

    @Test
    void testeMetodoCalcularValorVendaSemDesconto(){
        Venda venda = Builders.builderVendaDomain().get(1);
        venda.setValor(new BigDecimal(0));

        venda.calcularValorVenda();

        Assertions.assertEquals(720.0, venda.getValor().doubleValue());
    }

}