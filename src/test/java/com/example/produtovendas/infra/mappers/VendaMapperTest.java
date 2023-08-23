package com.example.produtovendas.infra.mappers;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendaMapperTest {

    @Test
    void testaSeRetornaUmEntity() {
        Long idCliente = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vitorhvvieira@gmail.com";
        String numeroTelefone = "vitorhvvieira@gmail.com";

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Long idVenda = 3L;
        ClienteEntity clienteVenda ;
        double valorVenda = 0;
        Integer desconto = 0;
        LocalDate dataVenda = LocalDate.now();
        List<Produto> produtoList = new ArrayList<>();
        List<ProdutoEntity> produtoEntities;

        Cliente cliente = new Cliente(idCliente, nome, inativo, cpf, email, numeroTelefone);
        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo, marcaProduto1, valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);
        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoEntities = ProdutoMapper.paraEntitys(produtoList);
        clienteVenda = ClienteMapper.paraEntity(cliente);
        Venda venda = new Venda(idVenda, cliente, clienteVenda.getId(), valorVenda, inativo, desconto, produtoList, dataVenda);
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);

        Assertions.assertEquals(idVenda, vendaEntity.getId());
        Assertions.assertEquals(clienteVenda, vendaEntity.getClienteEntity());
        Assertions.assertEquals(clienteVenda.getId(), vendaEntity.getClienteEntity().getId());
        Assertions.assertEquals(valorVenda, vendaEntity.getValor());
        Assertions.assertEquals(inativo, vendaEntity.isInativo());
        Assertions.assertEquals(desconto, vendaEntity.getDesconto());
        Assertions.assertEquals(produtoEntities, vendaEntity.getListaProdutos());
        Assertions.assertEquals(dataVenda, vendaEntity.getDataVenda());
    }

    @Test
    void testaSeRetornaUmDomain() {
        Long idCliente = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vitorhvvieira@gmail.com";
        String numeroTelefone = "vitorhvvieira@gmail.com";

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        Long idVenda = 3L;
        double valorVenda = 0;
        Integer desconto = 0;
        LocalDate dataVenda = LocalDate.now();
        List<Produto> produtoList = new ArrayList<>();

        ClienteEntity clienteEntity = new ClienteEntity(idCliente, nome, inativo, cpf, email, numeroTelefone);
        Cliente cliente = new Cliente(idCliente, nome, inativo, cpf, email, numeroTelefone);
        Produto produto1 = new Produto(idProduto1, nomeProduto1, inativo, marcaProduto1, valor1);
        Produto produto2 = new Produto(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);
        produtoList.add(produto1);
        produtoList.add(produto2);
        VendaEntity vendaEntity = new VendaEntity(idVenda, clienteEntity, inativo,  valorVenda, desconto, ProdutoMapper.paraEntitys(produtoList), dataVenda);
        Venda venda = VendaMapper.paraDomain(vendaEntity);
        List<Produto> produtos = venda.getListaProdutos();
        Produto produto3 = produtos.get(0);
        Produto produto4 = produtos.get(1);

        Assertions.assertEquals(idVenda, venda.getId());
        Assertions.assertEquals(cliente.getNome(), venda.getCliente().getNome());
        Assertions.assertEquals(cliente.getId(), venda.getCliente().getId());
        Assertions.assertEquals(cliente.isInativo(), venda.getCliente().isInativo());
        Assertions.assertEquals(cliente.getCpf(), venda.getCliente().getCpf());
        Assertions.assertEquals(cliente.getEmail(), venda.getCliente().getEmail());
        Assertions.assertEquals(cliente.getNumeroTelefone(), venda.getCliente().getNumeroTelefone());
        Assertions.assertEquals(inativo, venda.isInativo());
        Assertions.assertEquals(valorVenda, venda.getValor());
        Assertions.assertEquals(desconto, venda.getDesconto());
        Assertions.assertEquals(produto3, venda.getListaProdutos().get(0));
        Assertions.assertEquals(produto4, venda.getListaProdutos().get(1));
        Assertions.assertEquals(dataVenda, venda.getDataVenda());
    }

    @Test
    void testaSeRetornaUmaListaDeDomains() {
        Long idVenda = 1L;
        Long idCliente = 5L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vitorhvvieira@gmail.com";
        String numeroTelefone = "vitorhvvieira@gmail.com";

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        double valorVenda = 0;
        Integer desconto = 0;
        LocalDate dataVenda = LocalDate.now();

        ClienteEntity clienteEntity = new ClienteEntity(idCliente, nome, inativo, cpf, email, numeroTelefone);
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        produtoEntities.add(new ProdutoEntity(idProduto1, nomeProduto1, inativo,marcaProduto1, valor1));
        produtoEntities.add(new ProdutoEntity(idProduto2, nomeProduto2, inativo,marcaProduto2, valor2));

        List<VendaEntity> vendaEntities = new ArrayList<>();
        vendaEntities.add(new VendaEntity(idVenda, clienteEntity, inativo, valorVenda, desconto, produtoEntities, dataVenda));
        vendaEntities.add(new VendaEntity(idVenda, clienteEntity, inativo, valorVenda, desconto, produtoEntities, dataVenda));

        List<Venda> vendaList = VendaMapper.paraDomains(vendaEntities);
        List<Produto> produtoList = ProdutoMapper.paraProdutos(produtoEntities);

        Venda venda1 = vendaList.get(0);
        Venda venda2 = vendaList.get(1);

        Cliente clienteVenda = ClienteMapper.paraCliente(clienteEntity);
        Produto produto1 = produtoList.get(0);
        Produto produto2 = produtoList.get(1);

        Assertions.assertEquals(idVenda, venda1.getId());
        Assertions.assertEquals(clienteVenda.getNome(), venda1.getCliente().getNome());
        Assertions.assertEquals(clienteVenda.getId(), venda1.getCliente().getId());
        Assertions.assertEquals(clienteVenda.isInativo(), venda1.getCliente().isInativo());
        Assertions.assertEquals(clienteVenda.getCpf(), venda1.getCliente().getCpf());
        Assertions.assertEquals(clienteVenda.getEmail(), venda1.getCliente().getEmail());
        Assertions.assertEquals(clienteVenda.getNumeroTelefone(), venda1.getCliente().getNumeroTelefone());
        Assertions.assertEquals(inativo, venda1.isInativo());
        Assertions.assertEquals(valorVenda, venda1.getValor());
        Assertions.assertEquals(desconto, venda1.getDesconto());
        Assertions.assertEquals(produto1.getNome(), venda1.getListaProdutos().get(0).getNome());
        Assertions.assertEquals(produto1.getId(), venda1.getListaProdutos().get(0).getId());
        Assertions.assertEquals(produto1.getMarca(), venda1.getListaProdutos().get(0).getMarca());
        Assertions.assertEquals(produto1.getValor(), venda1.getListaProdutos().get(0).getValor());
        Assertions.assertEquals(produto1.isInativo(), venda1.getListaProdutos().get(0).isInativo());
        Assertions.assertEquals(produto2.getNome(), venda1.getListaProdutos().get(1).getNome());
        Assertions.assertEquals(produto2.getId(), venda1.getListaProdutos().get(1).getId());
        Assertions.assertEquals(produto2.getMarca(), venda1.getListaProdutos().get(1).getMarca());
        Assertions.assertEquals(produto2.getValor(), venda1.getListaProdutos().get(1).getValor());
        Assertions.assertEquals(produto2.isInativo(), venda1.getListaProdutos().get(1).isInativo());
        Assertions.assertEquals(dataVenda, venda1.getDataVenda());
        Assertions.assertEquals(clienteVenda.getNome(), venda2.getCliente().getNome());
        Assertions.assertEquals(clienteVenda.getId(), venda2.getCliente().getId());
        Assertions.assertEquals(clienteVenda.isInativo(), venda2.getCliente().isInativo());
        Assertions.assertEquals(clienteVenda.getCpf(), venda2.getCliente().getCpf());
        Assertions.assertEquals(clienteVenda.getEmail(), venda2.getCliente().getEmail());
        Assertions.assertEquals(clienteVenda.getNumeroTelefone(), venda2.getCliente().getNumeroTelefone());
        Assertions.assertEquals(inativo, venda2.isInativo());
        Assertions.assertEquals(valorVenda, venda2.getValor());
        Assertions.assertEquals(desconto, venda2.getDesconto());
        Assertions.assertEquals(produto1.getNome(), venda2.getListaProdutos().get(0).getNome());
        Assertions.assertEquals(produto1.getId(), venda2.getListaProdutos().get(0).getId());
        Assertions.assertEquals(produto1.getMarca(), venda2.getListaProdutos().get(0).getMarca());
        Assertions.assertEquals(produto1.getValor(), venda2.getListaProdutos().get(0).getValor());
        Assertions.assertEquals(produto1.isInativo(), venda2.getListaProdutos().get(0).isInativo());
        Assertions.assertEquals(produto2.getNome(), venda2.getListaProdutos().get(1).getNome());
        Assertions.assertEquals(produto2.getId(), venda2.getListaProdutos().get(1).getId());
        Assertions.assertEquals(produto2.getMarca(), venda2.getListaProdutos().get(1).getMarca());
        Assertions.assertEquals(produto2.getValor(), venda2.getListaProdutos().get(1).getValor());
        Assertions.assertEquals(produto2.isInativo(), venda2.getListaProdutos().get(1).isInativo());
        Assertions.assertEquals(dataVenda, venda2.getDataVenda());
    }
}