package com.example.produtovendas.service;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.entity.ClienteEntity;
import com.example.produtovendas.repository.ClienteRepository;
import com.example.produtovendas.repository.ProdutoRepository;
import com.example.produtovendas.repository.VendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private VendaRepository repositoryVenda;
    private ClienteRepository repositoryCliente;
    private ProdutoRepository repositoryProduto;
    private ItensVendaService itensVendaService;

    public ResponseEntity<Venda> cadastroVenda(Venda venda){
        ClienteEntity clienteEntity = repositoryCliente.getReferenceById(venda.getIdCliente());
    }



}
