package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.dtos.ProdutoDto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    public static final String MENSAGEM_PRODUTO_EXISTENTE = "Produto não existe";
    private final ProdutoDataProvider produtoDataProvider;
    private final EstoqueService estoqueService;


    public ProdutoDto cadastroProduto(ProdutoDto produtoDto) {
        Produto produto = ProdutoMapper.paraDomainDeDto(produtoDto);
        List<Produto> produtos = produtoDataProvider.consultaTodos();
        ProdutoValidation.validaProdutoExistente(produtos, produto);
        Produto produtoSalvo = produtoDataProvider.salvar(produto);
        estoqueService.criarEstoque(produtoSalvo);
        return ProdutoMapper.paraDtoDeDomain(produtoSalvo);
    }

    public ProdutoDto consultarProdutoPorId(Long id) {
        Optional<Produto> produto = produtoDataProvider.consultarPorId(id);
        if(produto.isPresent()){
            return ProdutoMapper.paraDtoDeDomain(produto.get());
        }else{
            throw new EntityNotFoundException(MENSAGEM_PRODUTO_EXISTENTE);
        }
    }

    public List<ProdutoDto> consultaTodosProdutos() {
        return ProdutoMapper.paraDtosDeDomains(produtoDataProvider.consultaTodos());
    }

    public void deletarProduto(Long id) {
        Produto produto = consultarProdutoExistentePorId(id);
        produto.inativar();
        produtoDataProvider.salvar(produto);
    }

    public ProdutoDto alterarProduto(Long id, ProdutoDto produtoDto) {
        Produto produto = consultarProdutoExistentePorId(id);
        produto.atualizaDados(ProdutoMapper.paraDomainDeDto(produtoDto));
        return ProdutoMapper.paraDtoDeDomain(produtoDataProvider.salvar(produto));
    }

    public Produto consultarProdutoExistentePorId(Long id) {
        return produtoDataProvider.consultarPorId(id).orElseThrow(() -> new RuntimeException(MENSAGEM_PRODUTO_EXISTENTE));
    }

    public void ativarProduto(Long id) {
        Produto produto = consultarProdutoExistentePorId(id);
        produto.ativar();
        produtoDataProvider.salvar(produto);
    }
}
