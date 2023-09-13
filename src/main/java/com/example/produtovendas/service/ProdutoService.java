package com.example.produtovendas.service;

import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.infra.dataproviders.ProdutoDataProvider;
import com.example.produtovendas.infra.validacoes.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class ProdutoService {

    public static final String MENSAGEM_PRODUTO_EXISTENTE = "Produto não existe";
    private ProdutoDataProvider produtoDataProvider;

    @Autowired
    public ProdutoService (ProdutoDataProvider produtoDataProvider){
        this.produtoDataProvider = produtoDataProvider;
    }

    public Produto cadastroProduto(Produto produto){
        List<Produto> produtos;
        produtos = produtoDataProvider.consultaTodos();
        ProdutoValidation.validaProduto(produtos, produto);
        return produtoDataProvider.salvar(produto);
    }

    public Produto consultarProdutoPorId(Long id){
        return produtoDataProvider.consultarPorId(id).orElseThrow(()-> new RuntimeException("Produto não existe"));
    }

    public List<Produto> consultaTodosProdutos() {
        return produtoDataProvider.consultaTodos();
    }

    public void deletarProduto(Long id){
        Produto produto = consultarProdutoPorId(id);
        produto.inativar();
        produtoDataProvider.salvar(produto);
    }

    public Produto alterarProduto(Long id, Produto produtoDto){
       Produto produto = consultarProdutoPorId(id);
       produto.atualizaDados(produtoDto);
       return produtoDataProvider.salvar(produto);
    }
}
