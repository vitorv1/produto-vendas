package com.example.produtovendas.infra.validacoes;

import com.example.produtovendas.domain.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class AtributeValidation {
    public static boolean longValidation(Long atribute){
       ValidationNull<Long> validationNull = new ValidationNull<>();
       return validationNull.validationAtribute(atribute);
    }

    public static boolean listValidation(List<Produto> atribute){
        ValidationNull<List<Produto>> validationNull = new ValidationNull<>();
        return validationNull.validationAtribute(atribute);
    }

    public static boolean integerValidation(Integer atribute){
        ValidationNull<Integer> validationNull = new ValidationNull<>();
        return validationNull.validationAtribute(atribute);
    }

    public static boolean stringValidation(String atribute){
        ValidationNull<String> validationNull = new ValidationNull<>();
        return validationNull.validationAtribute(atribute);
    }

    public static boolean bigDecimalValidation(BigDecimal atribute){
        ValidationNull<BigDecimal> validationNull = new ValidationNull<>();
        return validationNull.validationAtribute(atribute);
    }

    public static boolean objectValidation(Object object){
        ValidationNull<Object> validationNull = new ValidationNull<>();
        return validationNull.validationAtribute(object);
    }
}
