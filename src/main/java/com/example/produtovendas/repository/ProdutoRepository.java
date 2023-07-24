package com.example.produtovendas.repository;

import com.example.produtovendas.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{
}
