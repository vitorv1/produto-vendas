package com.example.produtovendas.validators;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Estoque;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.EstoqueEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public abstract class Validators {

   /* public static void validaClienteEntity(ClienteEntity cliente1, ClienteEntity cliente2){
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

    public static void validaProdutoEntity( ProdutoEntity produto, int index){
            Assertions.assertEquals(Builders.builderProdutoEntity().get(index).getId(), produto.getId());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(index).getNome(), produto.getNome());
            Assertions.assertFalse(produto.isInativo());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(index).getMarca(), produto.getMarca());
            Assertions.assertEquals(Builders.builderProdutoEntity().get(index).getValor(), produto.getValor());

    }

    public static void validaVendaEntity(VendaEntity venda, int index){
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getId(), venda.getId());
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getClienteEntity(), venda.getClienteEntity());
            Assertions.assertFalse(venda.isInativo());
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getValor(), venda.getValor());
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getDesconto(), venda.getDesconto());
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getListaProdutos(), venda.getListaProdutos());
            Assertions.assertEquals(Builders.builderVendaEntity().get(index).getDataVenda(), venda.getDataVenda());

    }

    public static void validaClienteDomain(Cliente cliente, int index){
            if(cliente.getId() == null){
                Assertions.assertNull(cliente.getId());
            }else {
                if(cliente.getId() == 1L){
                    Assertions.assertEquals(Builders.builderClienteDomain().get(0).getId(), cliente.getId());
                }else {
                    Assertions.assertEquals(Builders.builderClienteDomain().get(1).getId(), cliente.getId());
                }
            }
            Assertions.assertEquals(Builders.builderClienteDomain().get(index).getNome(), cliente.getNome());
            Assertions.assertFalse(cliente.isInativo());
            Assertions.assertEquals(Builders.builderClienteDomain().get(index).getCpf(), cliente.getCpf());
            Assertions.assertEquals(Builders.builderClienteDomain().get(index).getEmail(), cliente.getEmail());
            Assertions.assertEquals(Builders.builderClienteDomain().get(index).getNumeroTelefone(), cliente.getNumeroTelefone());

    }

    public static void validaProdutoDomain(Produto produto, int index){
            if(produto.getId() == 1L){
                Assertions.assertEquals(Builders.builderProdutoDomain().get(0).getId(), produto.getId());
            }else {
                Assertions.assertEquals(Builders.builderProdutoDomain().get(1).getId(), produto.getId());
            }
            Assertions.assertEquals(Builders.builderProdutoDomain().get(index).getNome(), produto.getNome());
            Assertions.assertFalse(produto.isInativo());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(index).getMarca(), produto.getMarca());
            Assertions.assertEquals(Builders.builderProdutoDomain().get(index).getValor(), produto.getValor());
    }

    public static void validaVendaDomain(Venda venda, int index){
            if(venda.getId() == null){
                Assertions.assertNull(venda.getId());
            }else{
                if(venda.getId() == 2L){
                    Assertions.assertEquals(Builders.builderVendaDomain().get(0).getId(), venda.getId());
                }else {
                    Assertions.assertEquals(Builders.builderVendaDomain().get(1).getId(), venda.getId());
                }
            }
            validaClienteDomain(venda.getCliente(), index);
            Assertions.assertEquals(Builders.builderVendaDomain().get(index).getValor(), venda.getValor());
            Assertions.assertFalse(venda.isInativo());
            Assertions.assertEquals(Builders.builderVendaDomain().get(index).getDesconto(), venda.getDesconto());
            validaProdutoDomain(venda.getListaProdutos().get(0), 0);
            validaProdutoDomain(venda.getListaProdutos().get(1), 1);
            Assertions.assertEquals(Builders.builderVendaDomain().get(index).getDataVenda(), venda.getDataVenda());

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

    public static void validaClienteController(ResultActions resultActions) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Builders.builderClienteEntity().get(0).getId()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(Builders.builderClienteEntity().get(0).getNome()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.inativo").value(Builders.builderClienteEntity().get(0).isInativo()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(Builders.builderClienteEntity().get(0).getCpf()));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.numeroTelefone").value(Builders.builderClienteEntity().get(0).getNumeroTelefone()));
    }

    public static void validaEstoqueDomain(Estoque estoque){
        if(estoque.getId() == null){
            Assertions.assertNull(estoque.getId());
        }else{
            Assertions.assertEquals(builderEstoqueDomain().getId(), estoque.getId());
        }
        validaProdutoDomain(estoque.getProduto(), 0);
        Assertions.assertEquals(builderEstoqueDomain().getQuantidade(), estoque.getQuantidade());
    }

    public static void validaEstoqueEntity(EstoqueEntity estoqueEntity){
        Assertions.assertEquals(builderEstoqueEntity().getId(), estoqueEntity.getId());
        validaProdutoEntity(estoqueEntity.getProdutoEntity(), 0);
        Assertions.assertEquals(builderEstoqueEntity().getQuantidade(), estoqueEntity.getQuantidade());
    }*/
}
