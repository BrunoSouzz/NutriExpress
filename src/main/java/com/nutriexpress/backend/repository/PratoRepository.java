package com.nutriexpress.backend.repository;

import com.nutriexpress.backend.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PratoRepository extends JpaRepository<Prato, Long> {
    List<Prato> findByCategoria(String categoria);
    boolean existsByNomeIgnoreCase(String nome);
}