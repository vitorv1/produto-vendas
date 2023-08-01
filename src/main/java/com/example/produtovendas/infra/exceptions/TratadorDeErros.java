package com.example.produtovendas.infra.exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao :: new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem){

        public DadosErroValidacao(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
