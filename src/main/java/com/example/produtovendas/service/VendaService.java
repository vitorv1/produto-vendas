package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.entity.VendaEntity;
import com.example.produtovendas.entity.VendaMapper;
import com.example.produtovendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repositoryVenda;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    public Venda cadastroVenda(Venda venda) {
        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        List<Produto> produtoList = venda.getListaProdutos().stream().map(produto -> produtoService.consultarProdutoPorId(produto.getId())).toList();
        venda.setListaProdutos(produtoList);
        venda.setDataVenda(LocalDate.now());
        venda.setValor(calcularValorVenda(venda));
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);
        repositoryVenda.save(vendaEntity);
        return VendaMapper.paraDomain(vendaEntity);
    }

    public static double calcularValorVenda(Venda venda) {
        double resultado;
        double valorSomaProdutos = 0;
        for (Produto produto : venda.getListaProdutos()) {
            valorSomaProdutos += produto.getValor();
        }
        if (venda.getDesconto() > 0) {
            double valorDesconto = (valorSomaProdutos * venda.getDesconto()) / 100;
            resultado = valorSomaProdutos - valorDesconto;
        } else {
            resultado = valorSomaProdutos;
        }
        return resultado;
    }


    public Venda buscarPorId(Long id) {
        return VendaMapper.paraDomain(repositoryVenda.findById(id).get());
    }

    public List<Venda> buscarTodos() {
        return VendaMapper.paraDomains(repositoryVenda.findAll());
    }

    public void deletarProduto(Long id) {
        repositoryVenda.deleteById(id);
    }

    public Venda alterarVenda(Long id, Venda venda) {

        VendaEntity vendaEntity = repositoryVenda.findById(id).get();
        venda.setId(vendaEntity.getId());

        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);

        List<Produto> produtoList = venda.getListaProdutos().stream().map(produto -> produtoService.consultarProdutoPorId(produto.getId())).toList();
        venda.setListaProdutos(produtoList);

        venda.setValor(calcularValorVenda(venda));

        repositoryVenda.save(VendaMapper.paraEntity(venda));
        return venda;
    }
}
