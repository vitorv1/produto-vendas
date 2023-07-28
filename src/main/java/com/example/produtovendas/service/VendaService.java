package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.entity.VendaEntity;
import com.example.produtovendas.entity.VendaMapper;
import com.example.produtovendas.repository.ClienteRepository;
import com.example.produtovendas.repository.ProdutoRepository;
import com.example.produtovendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repositoryVenda;
    @Autowired
    private ClienteRepository repositoryCliente;
    @Autowired
    private ProdutoRepository repositoryProduto;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    public Venda cadastroVenda(Venda venda){
        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        List<Produto> produtoList = venda.getListaProdutos().stream().map(produto -> produtoService.consultarProdutoPorId(produto.getId())).toList();
        venda.setListaProdutos(produtoList);
        venda.setDataVenda(LocalDate.now());
        VendaEntity vendaEntity = VendaMapper.paraEntity(calcularValorVenda(venda));
        repositoryVenda.save(vendaEntity);
        return VendaMapper.paraDomain(vendaEntity);
    }

    public static Venda calcularValorVenda(Venda venda){
        double valorSomaProdutos = 0;
        for(Produto produto : venda.getListaProdutos()){
             valorSomaProdutos += produto.getValor();
        }
        if(venda.getDesconto() > 0){
            double valorDesconto = (valorSomaProdutos * venda.getDesconto()) / 100;
            venda.setValor(valorSomaProdutos - valorDesconto);
        }else{
            venda.setValor(valorSomaProdutos);
        }
        return venda;
    }


    public Venda buscarPorId(Long id) {
        return VendaMapper.paraDomain(repositoryVenda.getReferenceById(id));
    }

    public List<Venda> buscarTodos() {
        return VendaMapper.paraDomains(repositoryVenda.findAll());
    }

    public void deletarProduto(Long id) {
        repositoryVenda.deleteById(id);
    }

    public Venda alterarVenda(Long id, Venda venda) {
        VendaEntity vendaEntity = repositoryVenda.getReferenceById(id);
        vendaEntity.alterarDados(venda.getIdCliente(), venda.getDesconto(), venda.getListaProdutos());
        Venda venda1 = calcularValorVenda(VendaMapper.paraDomain(vendaEntity));
        repositoryVenda.save(VendaMapper.paraEntity(venda1));
        return venda1;
    }
}
