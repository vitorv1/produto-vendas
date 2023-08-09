package com.example.produtovendas.infra.repositories;

import com.example.produtovendas.infra.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{
}
