package com.mapasocial.repository;

import com.mapasocial.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    List<Avaliacao> findByServicoId(UUID servicoId);
    Optional<Avaliacao> findByServicoIdAndUsuarioId(UUID servicoId, UUID usuarioId);

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.servico.id = :servicoId")
    Double calcularMediaNotas(UUID servicoId);
}
