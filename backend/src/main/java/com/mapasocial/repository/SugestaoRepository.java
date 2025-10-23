package com.mapasocial.repository;

import com.mapasocial.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, UUID> {
    List<Sugestao> findByStatus(String status);
    List<Sugestao> findByUsuarioId(UUID usuarioId);
}
