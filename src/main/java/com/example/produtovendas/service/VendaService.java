package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.entity.VendaEntity;
import com.example.produtovendas.entity.VendaMapper;
import com.example.produtovendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repositoryVenda;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    public Venda cadastroVenda(Venda venda)throws RuntimeException{
        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        System.out.println(venda);
        List<Produto> produtoList = new ArrayList<>();
        for (Produto produto : venda.getListaProdutos()){
            produtoList.add(produtoService.consultarProdutoPorId(produto.getId()));
        }
        venda.setListaProdutos(produtoList);
        venda.setDataVenda(LocalDate.now());
        venda.setValor(calcularValorVenda(venda.getDesconto(), venda.getListaProdutos()));
        System.out.println(venda);
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);
        try {
            repositoryVenda.save(vendaEntity);
        }catch (Exception ex){
            throw new RuntimeException("Erro no banco de dados");
        }
        return VendaMapper.paraDomain(vendaEntity);
    }

    public static double calcularValorVenda(Integer desconto, List<Produto> produtoList) {
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


    public Venda buscarPorId(Long id) {
        try{
           VendaEntity vendaEntity = repositoryVenda.findById(id).get();
            if(vendaEntity.getInativo()){
                return VendaMapper.paraDomain(vendaEntity);
            }else{
                throw new RuntimeException("Venda não encontrada");
            }
        }catch (Exception ex){
            throw new RuntimeException("Erro no banco de dados");
        }
    }

    public List<Venda> buscarTodos() throws RuntimeException{
        List<VendaEntity> vendas;
        try {
            vendas = repositoryVenda.findAll().stream().filter(VendaEntity -> !VendaEntity.getInativo()).toList();
        }catch (Exception ex){
            throw new RuntimeException("Erro no banco de dados");
        }
        return VendaMapper.paraDomains(vendas);
    }

    public void deletarVenda(Long id) throws RuntimeException {
        try {
            VendaEntity vendaEntity = repositoryVenda.findById(id).get();
            vendaEntity.setInativo(true);
            repositoryVenda.save(vendaEntity);
        }catch (Exception ex){
            throw new RuntimeException("Eerro no banco de dados");
        }
    }

    public Venda alterarVenda(Long id, Venda venda) throws RuntimeException {
        VendaEntity vendaEntity = null;
        try {
            vendaEntity = repositoryVenda.findById(id).get();
        }catch (Exception ex){
            throw new RuntimeException("Erro no banco de dados");
        }
        venda.setId(vendaEntity.getId());
        venda.setDataVenda(vendaEntity.getDataVenda());

        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);

        List<Produto> produtoList = venda.getListaProdutos().stream().map(produto -> produtoService.consultarProdutoPorId(produto.getId())).toList();
        venda.setListaProdutos(produtoList);

        venda.setValor(calcularValorVenda(venda.getDesconto(), venda.getListaProdutos()));
        try {
            repositoryVenda.save(VendaMapper.paraEntity(venda));
        }catch (Exception ex){
            throw new RuntimeException("Erro no banco de dados");
        }
        return venda;
    }
}
