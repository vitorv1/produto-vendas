package com.example.produtovendas.infra.repositories;

import com.example.produtovendas.infra.entities.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {
}
