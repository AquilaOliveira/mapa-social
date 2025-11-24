package com.mapa.social.demo.repository;

import com.mapa.social.demo.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    List<Solicitacao> findByUsuarioId(Integer usuarioId);
    List<Solicitacao> findByStatus(String status);
    List<Solicitacao> findByServicoSocialId(Integer servicoSocialId);
}
