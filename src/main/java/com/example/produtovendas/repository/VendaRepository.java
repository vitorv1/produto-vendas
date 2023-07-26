package com.example.produtovendas.repository;

import com.example.produtovendas.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<VendaEntity, Long> {
}
