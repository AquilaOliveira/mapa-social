package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.SugestaoServico;
import com.mapa.social.demo.model.SugestaoServico.StatusSugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SugestaoServicoRepository extends JpaRepository<SugestaoServico, Integer> {
    List<SugestaoServico> findByStatus(StatusSugestao status);
    List<SugestaoServico> findByUsuarioId(Integer usuarioId);
}
