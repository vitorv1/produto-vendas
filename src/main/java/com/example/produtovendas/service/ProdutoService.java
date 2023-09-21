package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    public static final String MENSAGEM_PRODUTO_EXISTENTE = "Produto não existe";
    private ProdutoDataProvider produtoDataProvider;

    @Autowired
    public ProdutoService(ProdutoDataProvider produtoDataProvider) {
        this.produtoDataProvider = produtoDataProvider;
    }

    public Produto cadastroProduto(Produto produto) {
        List<Produto> produtos = produtoDataProvider.consultaTodos();
        ProdutoValidation.validaProduto(produtos, produto);
        return produtoDataProvider.salvar(produto);
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
