package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {

    public static final String MENSAGEM_VENDA_EXISTE = "Venda não existe";
    public static final String MENSAGEM_PRODUTO_FALTA = "Produto em falta no estoque";

    private final VendaDataProvider vendaDataProvider;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final EstoqueService estoqueService;

    public Venda cadastroVenda(Venda venda) {
        definirClienteCadastro(venda);
        definirProdutosCadastro(venda);
        System.out.println(venda.getListaProdutos());
        venda.calcularValorVenda();
        venda.setDataVenda(LocalDate.now());
        return vendaDataProvider.salvar(venda);
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaDataProvider.buscarPorId(id);
    }

    public List<Venda> buscarTodos() {
        return vendaDataProvider.buscarTodos();
    }

    public void deletarVenda(Long id) {
        Venda venda = buscarExistentePorId(id);
        venda.inativar();
        vendaDataProvider.salvar(venda);
    }

    public Venda alterarVenda(Long id, Venda vendaAlterada) {
        Venda vendaExistente = buscarExistentePorId(id);
        definirClienteAlteracao(vendaAlterada, vendaExistente);
        definirProdutosAlteracao(vendaAlterada, vendaExistente);
        vendaExistente.atualizaDados(vendaAlterada);
        vendaExistente.calcularValorVenda();
        return vendaDataProvider.salvar(vendaExistente);
    }

    public Venda buscarExistentePorId(Long id) {
        return vendaDataProvider.buscarPorId(id).orElseThrow(() -> new RuntimeException("Venda não existe"));
    }

    private void definirClienteCadastro(Venda venda) {
        Cliente cliente = clienteService.consultaClienteExistentePorId(venda.getIdCliente());
        if (cliente.isInativo()) {
            throw new RuntimeException("Cliente está inativo, não pode realizar uma venda");
        }
        venda.setCliente(cliente);
    }

    private void definirProdutosCadastro(Venda venda) {
        List<Produto> produtoList = new ArrayList<>();
        venda.getListaProdutos().forEach((produto -> produtoList.add(produtoService.consultarProdutoExistentePorId(produto.getId()))));
        ProdutoValidation.validaProdutoInativo(produtoList);
        validaQuntidadeEstoqueProduto(produtoList);
        venda.setListaProdutos(estoqueService.definirQuantidadeEstoque(produtoList));
    }

    private void definirProdutosAlteracao(Venda vendaDto, Venda venda) {
        List<Produto> produtoListDto = new ArrayList<>();

        for (int i = 0; i < vendaDto.getListaProdutos().size(); i++) {
            if (!Objects.equals(venda.getListaProdutos().get(i).getId(), vendaDto.getListaProdutos().get(i).getId())) {
                Produto produto = produtoService.consultarProdutoExistentePorId(vendaDto.getListaProdutos().get(i).getId());
                produtoListDto.add(produto);
            }
        }
        if (produtoListDto.size() > 0) {
            vendaDto.setListaProdutos(produtoListDto);
        }
    }

    private void definirClienteAlteracao(Venda vendaDto, Venda venda) {
        if (!Objects.equals(vendaDto.getIdCliente(), venda.getIdCliente())) {
            Cliente cliente = clienteService.consultaClienteExistentePorId(vendaDto.getIdCliente());
            vendaDto.setCliente(cliente);
        }
    }

    private void validaQuntidadeEstoqueProduto(List<Produto> produtoList) {
        int repitidos = 1;
        for (int i = 0; i < produtoList.size(); i++) {
            Long id = produtoList.get(i).getId();
            for (int j = i + 1; j < produtoList.size(); j++) {
                if (Objects.equals(produtoList.get(j).getId(), id)){
                   repitidos ++;
                }
            }
            if(produtoList.get(i).getQuantidade() < repitidos)
                throw new RuntimeException(MENSAGEM_PRODUTO_FALTA);
        }

        List<Produto> produtosValidacao = produtoList.stream().filter(produto -> produto.getQuantidade() <= 0).toList();

            if (!produtosValidacao.isEmpty())
                throw new RuntimeException(MENSAGEM_PRODUTO_FALTA);

    }
}