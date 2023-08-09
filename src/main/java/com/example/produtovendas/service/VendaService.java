package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public Venda cadastroVenda(Venda venda) throws Exception {
        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        System.out.println(venda);
        List<Produto> produtoList;
         produtoList = venda.getListaProdutos().stream().map(Produto -> {
             try {
                 return produtoService.consultarProdutoPorId(Produto.getId());
             } catch (Exception e) {
                 throw new RuntimeException("Cliente não encontrado");
             }
         }).toList();
        venda.setListaProdutos(produtoList);
        venda.setDataVenda(LocalDate.now());
        venda.setValor(calcularValorVenda(venda.getDesconto(), venda.getListaProdutos()));
        System.out.println(venda);
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);
        try {
            repositoryVenda.save(vendaEntity);
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
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


    public Venda buscarPorId(Long id)throws SQLException {
        try{
           VendaEntity vendaEntity = repositoryVenda.findById(id).get();
            if(vendaEntity.getInativo()){
                return VendaMapper.paraDomain(vendaEntity);
            }else{
                throw new RuntimeException("Venda não encontrada");
            }
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
    }

    public List<Venda> buscarTodos() throws SQLException{
        List<VendaEntity> vendas;
        try {
            vendas = repositoryVenda.findAll().stream().filter(VendaEntity -> !VendaEntity.getInativo()).toList();
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        return VendaMapper.paraDomains(vendas);
    }

    public void deletarVenda(Long id) throws SQLException {
        try {
            VendaEntity vendaEntity = repositoryVenda.findById(id).get();
            vendaEntity.setInativo(true);
            repositoryVenda.save(vendaEntity);
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
    }

    public Venda alterarVenda(Long id, Venda venda) throws Exception {
        VendaEntity vendaEntity = null;
        try {
            vendaEntity = repositoryVenda.findById(id).get();
        }catch (Exception ex){
            throw new  BancoDeDadosException("Erro no banco de dados");
        }
        venda.setId(vendaEntity.getId());
        venda.setDataVenda(vendaEntity.getDataVenda());

        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        List<Produto> produtoList = venda.getListaProdutos().stream().map(produto -> {
            try {
                return produtoService.consultarProdutoPorId(produto.getId());
            } catch (Exception e) {
                throw new RuntimeException("Erro banco de dados");
            }
        }).toList();
        venda.setListaProdutos(produtoList);

        venda.setValor(calcularValorVenda(venda.getDesconto(), venda.getListaProdutos()));
        try {
            repositoryVenda.save(VendaMapper.paraEntity(venda));
        }catch (Exception ex){
            throw new BancoDeDadosException("Erro no banco de dados");
        }
        return venda;
    }
}
