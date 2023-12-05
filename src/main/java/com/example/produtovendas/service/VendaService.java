package com.example.produtovendas.service;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.dtos.VendaDto;
import com.example.produtovendas.infra.dataproviders.VendaDataProvider;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.validacoes.AtributeValidation;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class
VendaService {

    public static final String MENSAGEM_VENDA_EXISTE = "Venda não existe";
    public static final String MENSAGEM_PRODUTO_FALTA = "Produto em falta no estoque";

    private final VendaDataProvider vendaDataProvider;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final EstoqueService estoqueService;

    public VendaDto cadastroVenda(VendaDto vendaDto) {
        Venda venda = VendaMapper.paraDomainDeDto(vendaDto);
        definirClienteCadastro(venda);
        definirProdutosCadastro(venda);
        venda.calcularValorVenda();
        venda.setDataVenda(LocalDate.now());
        return VendaMapper.paraDtoDeDomain(vendaDataProvider.salvar(venda));
    }

    public VendaDto buscarPorId(Long id) {
        Optional<Venda> venda = vendaDataProvider.buscarPorId(id);
        if(venda.isPresent()){
            return VendaMapper.paraDtoDeDomain(venda.get());
        }else{
            throw new EntityNotFoundException(MENSAGEM_VENDA_EXISTE);
        }
    }

    public List<VendaDto> buscarTodos() {
        return VendaMapper.paraDtosDeDomains(vendaDataProvider.buscarTodos());
    }

    public void deletarVenda(Long id) {
        Venda venda = buscarExistentePorId(id);
        vendaDataProvider.deletar(venda);
    }

    public VendaDto alterarVenda(Long id, VendaDto vendaDto) {
        Venda vendaAlterada = VendaMapper.paraDomainDeDto(vendaDto);
        Venda vendaExistente = buscarExistentePorId(id);
        definirClienteAlteracao(vendaAlterada, vendaExistente);
        definirProdutosAlteracao(vendaAlterada, vendaExistente);
        vendaExistente.atualizaDados(vendaAlterada);
        vendaExistente.calcularValorVenda();
        return VendaMapper.paraDtoDeDomain(vendaDataProvider.salvar(vendaExistente));
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
       List<Produto> produtoList = new ArrayList<>();
        if(AtributeValidation.listValidation(vendaDto.getListaProdutos())){
            for (int i = 0; i < vendaDto.getListaProdutos().size(); i++) {
                if(AtributeValidation.longValidation(vendaDto.getListaProdutos().get(i).getId())){
                    if (!Objects.equals(venda.getListaProdutos().get(i).getId(), vendaDto.getListaProdutos().get(i).getId())) {
                        Produto produto = produtoService.consultarProdutoExistentePorId(vendaDto.getListaProdutos().get(i).getId());
                        produtoList.add(produto);
                    }
                }
            }
        }
        if(produtoList.size() > 0){
            vendaDto.setListaProdutos(produtoList);
        }
    }

    private void definirClienteAlteracao(Venda vendaDto, Venda venda) {
        if(AtributeValidation.longValidation(vendaDto.getIdCliente())){
            if (!Objects.equals(vendaDto.getIdCliente(), venda.getIdCliente())) {
                Cliente cliente = clienteService.consultaClienteExistentePorId(vendaDto.getIdCliente());
                vendaDto.setCliente(cliente);
            }
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