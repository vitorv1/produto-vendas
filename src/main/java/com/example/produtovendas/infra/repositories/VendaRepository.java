package com.example.produtovendas.infra.repositories;

import com.example.produtovendas.infra.entities.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Long> {
}
