package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaDataProvider vendaDataProvider;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Venda cadastroVenda(Venda venda){
        List<Produto> produtoList = new ArrayList<>();
        for(Produto produto : venda.getListaProdutos()){
            produtoList.add(produtoService.consultarProdutoPorId(produto.getId()));
        }
        venda.setListaProdutos(produtoList);
        venda.setValor(calcularValorVenda(venda.getDesconto(), produtoList));
        venda.setCliente(clienteService.consultaClientePorId(venda.getIdCliente()));
        venda.setDataVenda(LocalDate.now());
        return vendaDataProvider.salvar(venda);
    }

    public Venda buscarPorId(Long id){
        return vendaDataProvider.buscarPorId(id).orElseThrow(()-> new RuntimeException("Venda não existe"));
    }

    public List<Venda> buscarTodos(){
        return vendaDataProvider.buscarTodos();
    }

    public void deletarVenda(Long id){
        Venda venda = buscarPorId(id);
        venda.inativar();
        vendaDataProvider.salvar(venda);
    }

    public Venda alterarVenda(Long id, Venda vendaDto){
        Venda venda = buscarPorId(id);
        venda.atualizaDados(vendaDto);
        return vendaDataProvider.salvar(venda);
    }

    private static double calcularValorVenda(Integer desconto, List<Produto> produtoList) {
        double resultado;
        double valorSomaProdutos = 0;
        for (Produto produto : produtoList) {
            valorSomaProdutos += produto.getValor();
        }
        if (desconto > 0) {
            double valorDesconto = (valorSomaProdutos * desconto) / 100;
            resultado = valorSomaProdutos - valorDesconto;
        } else {
            resultado = valorSomaProdutos;
        }
        return resultado;
    }
}
