package com.example.produtovendas.validators;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public abstract class Validators {

    public static void validaClienteEntity(ClienteEntity cliente1, ClienteEntity cliente2){
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        if(cliente1 != null){
            clienteEntities.add(cliente1);
        }
        if(cliente2 != null){
            clienteEntities.add(cliente2);
        }
        for (int i = 0; i < clienteEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).getId(), clienteEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).getNome(), clienteEntities.get(i).getNome());
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).isInativo(), clienteEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).getCpf(), clienteEntities.get(i).getCpf());
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).getEmail(), clienteEntities.get(i).getEmail());
            Assertions.assertEquals(Builders.builderClienteEntity().get(i).getNumeroTelefone(), clienteEntities.get(i).getNumeroTelefone());
        }
    }

    public static void validaProdutoEntity( List<ProdutoEntity> produtoEntities){
        for (int i = 0; i < produtoEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderProdutoEntity().get(i).getId(), produtoEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(i).getNome(), produtoEntities.get(i).getNome());
            Assertions.assertFalse(produtoEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(i).getMarca(), produtoEntities.get(i).getMarca());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(i).getValor(), produtoEntities.get(i).getValor());
        }
    }

    public static void validaVendaEntity(VendaEntity venda1, VendaEntity venda2){
        List<VendaEntity> vendaEntities = new ArrayList<>();
        if(venda1 != null){
            vendaEntities.add(venda1);
        }
        if(venda2 != null){
            vendaEntities.add(venda2);
        }
        for (int i = 0; i < vendaEntities.size(); i++) {
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getId(), vendaEntities.get(i).getId());
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getClienteEntity(), vendaEntities.get(i).getClienteEntity());
            Assertions.assertFalse(vendaEntities.get(i).isInativo());
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getValor(), vendaEntities.get(i).getValor());
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getDesconto(), vendaEntities.get(i).getDesconto());
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getListaProdutos(), vendaEntities.get(i).getListaProdutos());
            Assertions.assertEquals(Builders.builderVendaEntity().get(i).getDataVenda(), vendaEntities.get(i).getDataVenda());
        }
    }

    public static void validaClienteDomain(Cliente cliente1, Cliente cliente2){
        List<Cliente> clienteList = new ArrayList<>();
        if(cliente1 != null) {
            clienteList.add(cliente1);
        }
        if(cliente2 != null){
            clienteList.add(cliente2);
        }
        for (int i = 0; i < clienteList.size(); i++) {
            if(clienteList.get(i).getId() == null){
                Assertions.assertNull(clienteList.get(i).getId());
            }else {
                System.out.println(i);
                Assertions.assertEquals(Builders.builderClienteDomain().get(i).getId(), clienteList.get(i).getId());
            }
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getNome(), clienteList.get(i).getNome());
            Assertions.assertFalse(clienteList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getCpf(), clienteList.get(i).getCpf());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getEmail(), clienteList.get(i).getEmail());
            Assertions.assertEquals(Builders.builderClienteDomain().get(i).getNumeroTelefone(), clienteList.get(i).getNumeroTelefone());
        }
    }

    public static void validaProdutoDomain(Produto produto1, Produto produto2){
        List<Produto> produtoList = new ArrayList<>();
        if(produto1 != null){
            produtoList.add(produto1);
        }
        if(produto2 != null){
            produtoList.add(produto2);
        }

        for (int i = 0; i < produtoList.size(); i++) {
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getId(), produtoList.get(i).getId());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getNome(), produtoList.get(i).getNome());
            Assertions.assertFalse(produtoList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getMarca(), produtoList.get(i).getMarca());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(i).getValor(), produtoList.get(i).getValor());
        }
    }

    public static void validaVendaDomain(Venda venda1, Venda venda2){
        List<Venda> vendaList = new ArrayList<>();

        if(venda1 != null){
            vendaList.add(venda1);
        }
        if(venda2 != null){
            vendaList.add(venda2);
        }

        for (int i = 0; i < vendaList.size(); i++) {
            if(vendaList.get(i).getId() == null){
                Assertions.assertNull(vendaList.get(i).getId());
            }else{
                Assertions.assertEquals(Builders.builderVendaDomain().get(i).getId(), vendaList.get(i).getId());
            }
            validaClienteDomain(vendaList.get(i).getCliente(), null);
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getValor(), vendaList.get(i).getValor());
            Assertions.assertFalse(vendaList.get(i).isInativo());
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getDesconto(), vendaList.get(i).getDesconto());
            validaProdutoEntity(ProdutoMapper.paraEntitys(vendaList.get(i).getListaProdutos()));
            Assertions.assertEquals(Builders.builderVendaDomain().get(i).getDataVenda(), vendaList.get(i).getDataVenda());
        }
    }

    public static void validaVendaController(ResultActions resultActions) throws Exception{
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderVendaEntity().get(0).getId())).
                andExpect(MockMvcResultMatchers.jsonPath("$.cliente").value(Builders.builderVendaEntity().get(0).getClienteEntity())).
                andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderVendaEntity().get(0).isInativo())).
                andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(Builders.builderVendaEntity().get(0).getValor())).
                andExpect(MockMvcResultMatchers.jsonPath("$.desconto").value(Builders.builderVendaEntity().get(0).getDesconto()));
        //andExpect(MockMvcResultMatchers.jsonPath("$.listaProdutos").value(Builders.builderVenda().get(0).getListaProdutos()));
    }

    public static void validaVendasController(ResultActions resultActions, String indexListVenda, VendaEntity vendaEntity) throws Exception{
        String index = "$[".concat(indexListVenda).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(vendaEntity.getId())).
                andExpect(jsonPath(index.concat("cliente")).value(vendaEntity.getClienteEntity())).
                andExpect(jsonPath(index.concat("inativo")).value(vendaEntity.isInativo())).
                andExpect(jsonPath(index.concat("valor")).value(vendaEntity.getValor())).
                andExpect(jsonPath(index.concat("desconto")).value(vendaEntity.getDesconto()));
        //andExpect(jsonPath(index.concat("listaProdutos")).value(vendaEntity.getListaProdutos())).
    }

    public static void validaProdutoController(ResultActions resultActions) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderProdutoEntity().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(Builders.builderProdutoEntity().get(0).getNome()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderProdutoEntity().get(0).isInativo()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.marca").value(Builders.builderProdutoEntity().get(0).getMarca()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(Builders.builderProdutoEntity().get(0).getValor()));
    }

    public static void validaProdutosController(ResultActions resultActions, String indexProdutoList, ProdutoEntity produto)throws Exception{
        String index = "$[".concat(indexProdutoList).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(produto.getId()))
                .andExpect(jsonPath(index.concat("nome")).value(produto.getNome()))
                .andExpect(jsonPath(index.concat("inativo")).value(produto.isInativo()))
                .andExpect(jsonPath(index.concat("marca")).value(produto.getMarca()))
                .andExpect(jsonPath(index.concat("valor")).value(produto.getValor()));
    }

    public static void validarAtributosDeCliente(ResultActions resultActions, String indexClienteList, ClienteEntity cliente) throws Exception {
        String index = "$[".concat(indexClienteList).concat("].");
        resultActions.andExpect(jsonPath(index.concat("id")).value(cliente.getId()))
                .andExpect(jsonPath(index.concat("nome")).value(cliente.getNome()))
                .andExpect(jsonPath(index.concat("inativo")).value(cliente.isInativo()))
                .andExpect(jsonPath(index.concat("cpf")).value(cliente.getCpf()))
                .andExpect(jsonPath(index.concat("email")).value(cliente.getEmail()))
                .andExpect(jsonPath(index.concat("numeroTelefone")).value(cliente.getNumeroTelefone()));
    }

    public static void validaCliente(ResultActions resultActions) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderClienteEntity().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(Builders.builderClienteEntity().get(0).getNome()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderClienteEntity().get(0).isInativo()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(Builders.builderClienteEntity().get(0).getCpf()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefone").value(Builders.builderClienteEntity().get(0).getNumeroTelefone()));
    }

    public static void validaVendaDomainAlterado(Venda venda){
            Assertions.assertEquals(Builders.builderVendaDomain().get(0).getId(), venda.getId());
            validaClienteDomainAlterado(venda.getCliente());
            Assertions.assertEquals(Builders.builderVendaDomain().get(1).getValor(), venda.getValor());
            Assertions.assertFalse(venda.isInativo());
            Assertions.assertEquals(Builders.builderVendaDomain().get(1).getDesconto(), venda.getDesconto());
            validaProdutoDomain(venda.getListaProdutos().get(0), venda.getListaProdutos().get(1));
            Assertions.assertEquals(Builders.builderVendaDomain().get(1).getDataVenda(), venda.getDataVenda());

    }

    public static void validaClienteDomainAlterado(Cliente cliente) {
        if(cliente.getId() == 1L){
            Assertions.assertEquals(Builders.builderClienteDomain().get(0).getId(), cliente.getId());
        }else {
            Assertions.assertEquals(Builders.builderClienteDomain().get(1).getId(), cliente.getId());
        }
        Assertions.assertEquals(Builders.builderClienteDomain().get(1).getNome(), cliente.getNome());
        Assertions.assertFalse(cliente.isInativo());
        Assertions.assertEquals(Builders.builderClienteDomain().get(1).getCpf(), cliente.getCpf());
        Assertions.assertEquals(Builders.builderClienteDomain().get(1).getEmail(), cliente.getEmail());
        Assertions.assertEquals(Builders.builderClienteDomain().get(1).getNumeroTelefone(), cliente.getNumeroTelefone());
    }

    public static void validaProdutoDomainAlterado(Produto produto){
        Assertions.assertEquals(Builders.builderProdutoDomain().get(0).getId(), produto.getId());
        Assertions.assertEquals(Builders.builderProdutoDomain().get(1).getNome(), produto.getNome());
        Assertions.assertFalse(produto.isInativo());
        Assertions.assertEquals(Builders.builderProdutoDomain().get(1).getMarca(), produto.getMarca());
        Assertions.assertEquals(Builders.builderProdutoDomain().get(1).getValor(), produto.getValor());
    }
}
