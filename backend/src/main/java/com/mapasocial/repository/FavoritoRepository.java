package com.mapasocial.repository;

import com.mapasocial.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, UUID> {
    List<Favorito> findByUsuarioId(UUID usuarioId);
    Optional<Favorito> findByUsuarioIdAndServicoId(UUID usuarioId, UUID servicoId);
    boolean existsByUsuarioIdAndServicoId(UUID usuarioId, UUID servicoId);
}
