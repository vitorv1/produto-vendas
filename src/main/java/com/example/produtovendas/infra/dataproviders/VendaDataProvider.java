package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.repositories.VendaRepository;
import com.example.produtovendas.service.ClienteService;
import com.example.produtovendas.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class VendaDataProvider {


    private final VendaRepository repositoryVenda;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    @Autowired
    public VendaDataProvider (VendaRepository repositoryVenda, ClienteService clienteService, ProdutoService produtoService){
        this.repositoryVenda = repositoryVenda;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public Venda cadastroVenda(Venda venda){
        Cliente cliente = clienteService.consultaClientePorId(venda.getIdCliente());
        venda.setCliente(cliente);
        List<Produto> produtoList;
        produtoList = venda.getListaProdutos().stream().map(Produto -> {
            try {
                return produtoService.consultarProdutoPorId(Produto.getId());
            } catch (Exception ex) {
                log.info(ex.getMessage());
                throw new RuntimeException("Cliente não encontrado");
            }
        }).toList();
        venda.setListaProdutos(produtoList);
        venda.setDataVenda(LocalDate.now());
        venda.setValor(calcularValorVenda(venda.getDesconto(), venda.getListaProdutos()));
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);
        try {
            repositoryVenda.save(vendaEntity);
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao salvar venda no banco de dados");
        }
        return venda;
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

    public Venda buscarPorId(Long id){
        try{
            return VendaMapper.paraDomain(repositoryVenda.findById(id).get());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro ao consultar por id no banco de dados");
        }
    }

    public List<Venda> buscarTodos(){
        try {
            return VendaMapper.paraDomains(repositoryVenda.findAll());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro ao consultar todos no banco de dados");
        }
    }

}
