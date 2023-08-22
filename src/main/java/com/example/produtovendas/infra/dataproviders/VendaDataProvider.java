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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VendaDataProvider {
    @Autowired
    private final VendaRepository repositoryVenda;
    @Autowired
    private final ClienteService clienteService;
    @Autowired
    private final ProdutoService produtoService;


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
        return VendaMapper.paraDomain(vendaEntity);
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

    public void deletarVenda(Long id) {
        try {
            Venda venda = VendaMapper.paraDomain(repositoryVenda.findById(id).get());
            venda.inativar();
            repositoryVenda.save(VendaMapper.paraEntity(venda));
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro ao salvar venda no banco de dados");
        }
    }

    public Venda alterarVenda(Long id, Venda venda) {
        VendaEntity vendaEntity;
        try {
            vendaEntity = repositoryVenda.findById(id).get();
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new  BancoDeDadosException("Erro ao consultar por id venda no banco de dados");
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
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao atualizar venda no banco de dados");
        }
        return venda;
    }
}
