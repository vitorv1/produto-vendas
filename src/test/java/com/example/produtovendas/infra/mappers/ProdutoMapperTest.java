package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.validators.Validators;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.produtovendas.validators.Validators.*;

class ProdutoMapperTest {

    @Test
    void testaSeRetornaUmProdutoDomain() {
        Produto produto = ProdutoMapper.paraDomain(Builders.builderProdutoEntity().get(0));
        validaProdutoDomain(produto, 0);
    }

    @Test
    void testaSeRetornaUmProdutoEntity() {
        ProdutoEntity produtoEntity = ProdutoMapper.paraEntity(Builders.builderProdutoDomain().get(0));
        validaProdutoEntity(produtoEntity, 0);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomains() {
        List<Produto> produtoList = ProdutoMapper.paraDomains(Builders.builderProdutoEntity());
        validaProdutoDomain(produtoList.get(0), 0);
        validaProdutoDomain(produtoList.get(1), 1);
    }

    @Test
    void testeSeRetornaUmProdutoEntitys() {
        List<ProdutoEntity> produtoList = ProdutoMapper.paraEntitys(Builders.builderProdutoDomain());
        validaProdutoEntity(produtoList.get(0), 0);
        validaProdutoEntity(produtoList.get(1), 1);
    }

    @Test
    void testaSeRetornaUmProdutoDomainDeUmDto(){
        ProdutoDto produtoDto = Builders.builderProdutoDto().get(0);
        Produto produtoTeste = ProdutoMapper.paraDomainDeDto(produtoDto);
        Validators.validaProdutoDomain(produtoTeste, 0);
    }

    @Test
    void testaSeRetornaUmProdutoDtoDeUmDomain(){
        Produto produto = Builders.builderProdutoDomain().get(0);
        ProdutoDto produtoTeste = ProdutoMapper.paraDtoDeDomain(produto);
        Validators.validaProdutoDto(produtoTeste, 0);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDtosDeUmaListaDeDomains(){
        List<Produto> produtoList = Builders.builderProdutoDomain();
        List<ProdutoDto> listTeste = ProdutoMapper.paraDtosDeDomains(produtoList);
        Validators.validaProdutoDto(listTeste.get(0), 0);
        Validators.validaProdutoDto(listTeste.get(1), 1);
    }

    @Test
    void testaSeRetornaUmaListaDeProdutosDomainsDeUmaListaDeDtos(){
        List<ProdutoDto> produtoDtos = Builders.builderProdutoDto();
        List<Produto> listTeste = ProdutoMapper.paraDomainsDeDtos(produtoDtos);
        Validators.validaProdutoDomain(listTeste.get(0), 0);
        Validators.validaProdutoDomain(listTeste.get(1), 1);
    }
}