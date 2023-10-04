package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
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


    public Produto cadastroProduto(Produto produto) {
        List<Produto> produtos = produtoDataProvider.consultaTodos();
        ProdutoValidation.validaProduto(produtos, produto);
        Produto produtoSalvo = produtoDataProvider.salvar(produto);
        estoqueService.criarEstoque(produtoSalvo);
        return produtoSalvo;
    }

    public Optional<Produto> consultarProdutoPorId(Long id) {
        return produtoDataProvider.consultarPorId(id);
    }

    public List<Produto> consultaTodosProdutos() {
        return produtoDataProvider.consultaTodos();
    }

    public void deletarProduto(Long id) {
        Produto produto = consultarProdutoExistentePorId(id);
        produto.inativar();
        produtoDataProvider.salvar(produto);
    }

    public Produto alterarProduto(Long id, Produto produtoDto) {
        Produto produto = consultarProdutoExistentePorId(id);
        produto.atualizaDados(produtoDto);
        return produtoDataProvider.salvar(produto);
    }

    public Produto consultarProdutoExistentePorId(Long id) {
        return produtoDataProvider.consultarPorId(id).orElseThrow(() -> new RuntimeException(MENSAGEM_PRODUTO_EXISTENTE));
    }
}
